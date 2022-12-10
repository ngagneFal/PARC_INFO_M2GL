package sn.isi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;

import sn.isi.domaine.User;
import sn.isi.entities.UserEntity;

public interface UserRepository {

	Page<User> findAll();

	Optional<User> findById(Long id);

	void deleteById(Long id);

	User findByFirstName(String firstName);

	UserEntity save(UserEntity fromUser);

}
