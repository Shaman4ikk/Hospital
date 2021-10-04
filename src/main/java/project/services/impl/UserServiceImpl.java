package project.services.impl;

import org.springframework.stereotype.Service;
import project.dto.UserDTO;
import project.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDTO> getUsers() {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO create(UserDTO patient) {
        return null;
    }

    @Override
    public boolean update(UserDTO patient) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
