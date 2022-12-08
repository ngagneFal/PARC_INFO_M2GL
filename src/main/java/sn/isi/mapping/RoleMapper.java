package sn.isi.mapping;


import org.mapstruct.Mapper;
import sn.isi.domaine.Role;
import sn.isi.entities.RoleEntity;

@Mapper
public interface RoleMapper {
    Role toRole(RoleEntity roleEntity);
    RoleEntity fromRole(Role role);
}
