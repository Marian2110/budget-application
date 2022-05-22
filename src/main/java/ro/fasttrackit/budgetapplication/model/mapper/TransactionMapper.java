package ro.fasttrackit.budgetapplication.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.fasttrackit.budgetapplication.model.dto.TransactionDTO;
import ro.fasttrackit.budgetapplication.model.entity.Transaction;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    TransactionDTO mapToDTO(Transaction transaction);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "userId", target = "user.id")
    Transaction mapToEntity(TransactionDTO transactionDTO);
}
