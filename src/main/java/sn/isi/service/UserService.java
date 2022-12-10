package sn.isi.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.mapstruct.Mapper;
import lombok.AllArgsConstructor;
import sn.isi.domaine.User;
import sn.isi.mapping.UserMapper;
import sn.isi.repository.RoleRepository;
import sn.isi.repository.UserRepository;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class UserService {
	UserRepository userRepository;
    UserMapper userMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userMapper.toUser(userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public User createUser(User user) {
      /*  userRepository.findById(user.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("user.exists", new Object[]{user.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });*/
        return userMapper.toUser(userRepository.save(userMapper.fromUser(user)));
    }

    @Transactional
    public User updateUser(Long id, User user){
        return userRepository.findById(id)
                .map(entity -> {
                    user.setId(id);
                    return userMapper.toUser(userRepository.save(userMapper.fromUser(user)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteUser(Long id) throws NoSuchMessageException, Exception {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()));
                   
        }
    }
}
