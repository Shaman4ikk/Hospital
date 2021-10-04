package project.services;

import project.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();
    UserDTO getUserById(Long id);
    UserDTO create(UserDTO patient);
    boolean update(UserDTO patient);
    boolean delete(Long id);
}
