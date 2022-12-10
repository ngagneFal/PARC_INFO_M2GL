package sn.isi.mapping;


import java.util.Optional;

import org.mapstruct.Mapper;
import sn.isi.domaine.Role;
import sn.isi.entities.RoleEntity;

@Mapper
public interface RoleMapper {
    Role toRole(RoleEntity roleEntity);
    RoleEntity fromRole(Role role);
	Role toRole(Role orElseThrow);
	Role toRole(Optional<Role> findByRole_titleIgnoreCase);
	Role toCredit(RoleEntity save);
}
