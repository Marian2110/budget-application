package ro.fasttrackit.budgetapplication.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.fasttrackit.budgetapplication.model.dto.RoleDTO;
import ro.fasttrackit.budgetapplication.model.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Mapping(target = "id", source = "role.id")
    Role mapToEntity(RoleDTO role);

    RoleDTO mapToDTO(Role role);


    List<RoleDTO> mapToDTOs(List<Role> roles);
}
