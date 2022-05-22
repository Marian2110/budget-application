package ro.fasttrackit.budgetapplication.controller.role;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.budgetapplication.model.entity.Role;
import ro.fasttrackit.budgetapplication.service.role.RoleService;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@AllArgsConstructor
@Validated
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getRoles();
    }

    @GetMapping(path = "/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRole(id);
    }

    @PostMapping
    public Role addRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping(path = "/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping(path = "/{id}")
    public Role deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }
}