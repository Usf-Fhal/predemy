package users.services;

import users.entities.User;
import users.dto.UserDto;
import users.entities.Role;
import users.entities.Status;

public interface UserService {
    User saveUser(User user);
    void deleteUser(String uuid);
    User changeRole(String uuid, Role newRole);
    User changeStatus(String uuid, Status newStatus);
    User findByUuid(String uuid);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}