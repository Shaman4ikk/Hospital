package project.mappers;

import org.mapstruct.Mapper;
import project.dto.UserDTO;
import project.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToDTO(User user);
    User dtoToUser(UserDTO userDTO);
    List<UserDTO> usersToDTO(List<User> users);

}
