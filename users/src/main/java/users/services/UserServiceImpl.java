package users.services;

import users.entities.User;
import users.entities.Role;
import users.entities.Status;
import users.entities.Subscription;
import users.repositories.SubscriptionRepository;
import users.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;


    public UserServiceImpl(UserRepository userRepository , SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
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

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByUsernameAndPassword(email, password)
            .orElseThrow(() -> new EntityNotFoundException("User not found with provided email and password."));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    /**
     * Allows a student to subscribe to an instructor.
     * 
     * @param instructor the instructor to subscribe to
     * @return the created Subscription object
     */
    public Subscription subscribe  (User student, User instructor) {
        if (student == null || student.getRole() != Role.STUDENT) {
            throw new IllegalStateException("Only users with STUDENT role can subscribe to instructors.");
        }
        if (instructor == null || instructor.getRole() != Role.INSTRUCTOR) {
            throw new IllegalArgumentException("Target user must be an INSTRUCTOR.");
        }

        Subscription subscription = new Subscription();
        subscription.setStudent(student);
        subscription.setInstructor(instructor);
        // Optionally set other fields like subscription date, status, etc.

        // Add to both sides of the relationship if collections exist
        if (student.getStudentSubscriptions() != null) {
            student.getStudentSubscriptions().add(subscription);
        }
        if (instructor.getInstructorSubscriptions() != null) {
            instructor.getInstructorSubscriptions().add(subscription);
        }
        // Save the instructor and student to ensure the relationship is persisted
        userRepository.save(student);
        userRepository.save(instructor);
        // Save the subscription to the database
        subscriptionRepository.save(subscription);

        return subscription;
    }
    
}