package sn.isi.service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import sn.isi.domaine.Role;
import sn.isi.mapping.RoleMapper;
import sn.isi.repository.RoleRepository;
import sn.isi.repository.UserRepository;

@Service
@AllArgsConstructor
public class RoleService {
	
	RoleRepository roleRepository;
    UserRepository userRepository;
    RoleMapper roleMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                .map(roleMapper::toRole)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Role getRole(String role_title) {
        return roleMapper.toRole(roleRepository.findByRole_titleIgnoreCase(role_title));
    }

    @Transactional
    public Role createRole(Role role) {
        
        roleRepository.findByRole_titleIgnoreCase(role.getRole_title())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("role.exists", new Object[]{role.getRole_title()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return roleMapper.toRole(roleRepository.save(roleMapper.fromRole(role)));
    }

    @Transactional
    public Role updateRole(String role_title, Role role) {
        return roleRepository.findByRole_titleIgnoreCase(role_title)
                .map(entity -> {
                    role.setRole_title(role_title);
                    return roleMapper.toRole(
                            roleRepository.save(roleMapper.fromRole(role)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{role_title},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteRole(String role_title) throws NoSuchMessageException, Exception {
        try {
            roleRepository.deleteById(role_title);
        } catch (Exception e) {
            throw new Exception(messageSource.getMessage("credit.errordeletion", new Object[]{role_title},
                    Locale.getDefault()));
        }
    }

}
