package users.services;

import users.entities.User;
import users.entities.Role;
import users.entities.Status;
import users.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String uuid) {
        User user = findByUuid(uuid);
        userRepository.delete(user);
    }

    @Override
    public User changeRole(String uuid, Role newRole) {
        User user = findByUuid(uuid);
        user.setRole(newRole);
        return userRepository.save(user);
    }

    @Override
    public User changeStatus(String uuid, Status newStatus) {
        User user = findByUuid(uuid);
        user.setStatus(newStatus);
        return userRepository.save(user);
    }

    @Override
    public User findByUuid(String uuid) {
        return userRepository.findById(uuid)
            .orElseThrow(() -> new EntityNotFoundException("User not found with uuid: " + uuid));
    }
}