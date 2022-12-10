package sn.isi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;

import sn.isi.domaine.Role;
import sn.isi.entities.RoleEntity;

public interface RoleRepository {

	Optional<Role> findByRole_titleIgnoreCase(String role_title);

	void deleteById(String role_title);

	RoleEntity save(RoleEntity fromRole);

	Page<Role> findAll();

	Role findByRole_title(String role_title);
}
