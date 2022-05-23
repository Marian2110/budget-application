package ro.fasttrackit.budgetapplication.service.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.budgetapplication.model.entity.Transaction;
import ro.fasttrackit.budgetapplication.utils.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@AllArgsConstructor
public class TransactionDao {

    private final EntityManager entityManager;

    public List<Transaction> find(Criteria criteria) {
        StringBuilder query = new StringBuilder("SELECT t FROM Transaction t ");
        if (criteria.getFilterOptions() != null) {
            query.append("WHERE ");
            criteria.getFilterOptions().forEach(filterOption -> {
                query
                        .append(filterOption.getFieldName())
                        .append(" ").append(filterOption.getOperator())
                        .append(" ").append(filterOption.getValue()).append(" ");
            });
        }
        if (criteria.getSortOptions() != null) {
            query.append("ORDER BY ");
            criteria.getSortOptions().forEach(sortOption -> {
                query
                        .append(sortOption.getProperty()).append(" ")
                        .append(sortOption.getDirection()).append(" ");
            });
        }

        if (criteria.getSize() > 0) {
            query.append("LIMIT ").append(criteria.getSize()).append(" ");
        }

        if (criteria.getPage() > 0) {
            query.append("OFFSET ").append(criteria.getPage() * criteria.getSize()).append(" ");
        }

        return entityManager.createQuery(query.toString(), Transaction.class).getResultList();
    }

    public List<Transaction> findUsingCriteriaBuilder(Criteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Transaction.class)));
        TypedQuery<Long> countTypedQuery = entityManager.createQuery(countQuery);
        Long totalCount = countTypedQuery.getSingleResult();
        int totalPages = (int) Math.ceil(totalCount / criteria.getSize());

        CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

        Root<Transaction> transaction = criteriaQuery.from(Transaction.class);

        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getFilterOptions() != null) {
            List<Predicate> filterPredicates = new ArrayList<>();
            criteria.getFilterOptions().forEach(filterOption -> {
                        if (filterOption.getOperator().equals("=")) {
                            predicates.add(cb.equal(transaction.get(filterOption.getFieldName()), filterOption.getValue()));
                        }
                        if (filterOption.getOperator().equals("<")) {
                            predicates.add(cb.lessThan(transaction.get(filterOption.getFieldName()), filterOption.getValue()));
                        }
                        if (filterOption.getOperator().equals(">")) {
                            predicates.add(cb.greaterThan(transaction.get(filterOption.getFieldName()), filterOption.getValue()));
                        }
                        if (filterOption.getOperator().equals("<=")) {
                            predicates.add(cb.lessThanOrEqualTo(transaction.get(filterOption.getFieldName()), filterOption.getValue()));
                        }
                        if (filterOption.getOperator().equals(">=")) {
                            predicates.add(cb.greaterThanOrEqualTo(transaction.get(filterOption.getFieldName()), filterOption.getValue()));
                        }
                        if (filterOption.getOperator().equalsIgnoreCase("LIKE")) {
                            predicates.add(cb.like(transaction.get(filterOption.getFieldName()),
                                    "%" + filterOption.getValue() + "%"));
                        }
                        if (filterOption.getOperator().equalsIgnoreCase("NOT LIKE")) {
                            predicates.add(cb.notLike(transaction.get(filterOption.getFieldName()),
                                    "%" + filterOption.getValue() + "%"));

                        }
                        if (filterOption.getOperator().equalsIgnoreCase("IN")) {
                            predicates.add(transaction.get(filterOption.getFieldName()).in(filterOption.getValue()));
                        }
                    }
            );
        }

        List<Order> orders = new ArrayList<>();
        if (criteria.getSortOptions() != null) {
            criteria.getSortOptions().forEach(sortOption -> {

                if (sortOption.getDirection().equalsIgnoreCase("asc")) {
                    orders.add(cb.asc(transaction.get(sortOption.getProperty())));
                }
                if (sortOption.getDirection().equalsIgnoreCase("desc")) {
                    orders.add(cb.desc(transaction.get(sortOption.getProperty())));
                }
            });
        }

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicates.toArray(predicatesArray);

        criteriaQuery.where(predicatesArray);
        criteriaQuery.orderBy(orders);

        TypedQuery<Transaction> typedQuery = entityManager.createQuery(criteriaQuery);

        if (criteria.getPage() < totalPages) {
            typedQuery.setFirstResult((criteria.getPage() - 1) * criteria.getSize());
            typedQuery.setMaxResults(criteria.getSize());
        } else {
            typedQuery.setFirstResult((totalPages - 1) * criteria.getSize());
            typedQuery.setMaxResults(totalPages * criteria.getSize());
        }
        log.info("Query: {}", typedQuery.unwrap(org.hibernate.Query.class).getQueryString());
        log.info(" totalPages " + totalPages + " " + criteria.getPage() + " " + criteria.getSize());
        return typedQuery.getResultList();
    }
}