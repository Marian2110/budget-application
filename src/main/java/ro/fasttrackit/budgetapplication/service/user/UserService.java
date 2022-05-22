package ro.fasttrackit.budgetapplication.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.budgetapplication.model.entity.Role;
import ro.fasttrackit.budgetapplication.model.entity.User;
import ro.fasttrackit.budgetapplication.exception.EntityNotFoundException;
import ro.fasttrackit.budgetapplication.service.role.RoleService;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private RoleService roleService;

    public User addUser(User user) {
        log.info("Creating user {}", user);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        log.info("Getting user {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.forEntity(User.class, id));
    }

    public User updateUser(Long id, User user) {
        log.info("Updating user {}", id);
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    // TODO hashing password
                    existingUser.setPassword(user.getPassword());
                    existingUser.setRoles(user.getRoles());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> EntityNotFoundException.forEntity(User.class, id));
    }

    public User deleteUser(Long id) {
        log.info("Deleting user {}", id);
        return userRepository
                .findById(id)
                .map(existingUser -> {
                    userRepository.delete(existingUser);
                    return existingUser;
                }).orElseThrow(() -> EntityNotFoundException.forEntity(User.class, id));
    }

    public User addRole(Long id, Long roleId) {
        log.info("Adding role {} to user {}", roleId, id);
        User user = getUser(id);
        Role role = roleService.getRole(roleId);
        List<Role> roles = getRoles(id);
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public List<Role> getRoles(Long id) {
        return userRepository.findById(id)
                .map(User::getRoles)
                .orElseThrow(() -> EntityNotFoundException.forEntity(User.class, id));
    }

    public User removeRole(Long id, Long roleId) {
        log.info("Removing role {} from user {}", roleId, id);
        User user = getUser(id);
        Role role = roleService.getRole(roleId);
        List<Role> roles = getRoles(id);
        roles.remove(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

}
