package sn.isi.mapping;

import org.mapstruct.Mapper;
import sn.isi.domaine.User;
import sn.isi.entities.UserEntity;

@Mapper
public interface UserMapper {
    User toUser(UserEntity userEntity);
    UserEntity fromUser(User user);
}
