package users;

import users.entities.User;
import users.entities.Role;
import users.entities.Status;
import users.repositories.UserRepository;
import users.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import jakarta.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        // Clean up the test database
        userRepository.deleteAll();

        // Create a test user
        testUser = new User(
            "John",
            "Doe",
            "john.doe@test.com",
            "password123"
        );
    }

    @Test
    void saveUser_WithValidData_ShouldSaveSuccessfully() {
        // Act
          
        User savedUser = userService.saveUser(testUser);

        // Assert
        assertNotNull(savedUser.getUuid());
        assertEquals(testUser.getEmail(), savedUser.getEmail());
        assertEquals(Role.STUDENT, savedUser.getRole());
        assertEquals(Status.ACTIVE, savedUser.getStatus());
    }

    @Test
    void saveUser_WithDuplicateEmail_ShouldThrowException() {
        // Arrange
        userService.saveUser(testUser);
        User duplicateUser = new User(
            "Jane",
            "Doe",
            "john.doe@test.com", // Same email
            "password456"
        );

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(duplicateUser);
        });
    }

    @Test
    void changeRole_WithValidUuid_ShouldUpdateRole() {
        // Arrange
        User savedUser = userService.saveUser(testUser);

        // Act
        User updatedUser = userService.changeRole(savedUser.getUuid(), Role.INSTRUCTOR);

        // Assert
        assertEquals(Role.INSTRUCTOR, updatedUser.getRole());
    }

    @Test
    void changeStatus_WithValidUuid_ShouldUpdateStatus() {
        // Arrange
        User savedUser = userService.saveUser(testUser);

        // Act
        User updatedUser = userService.changeStatus(savedUser.getUuid(), Status.INACTIVE);

        // Assert
        assertEquals(Status.INACTIVE, updatedUser.getStatus());
    }

    @Test
    void deleteUser_WithValidUuid_ShouldDeleteUser() {
        // Arrange
        User savedUser = userService.saveUser(testUser);

        // Act
        userService.deleteUser(savedUser.getUuid());

        // Assert
        assertThrows(EntityNotFoundException.class, () -> {
            userService.findByUuid(savedUser.getUuid());
        });
    }

    @Test
    void findByUuid_WithInvalidUuid_ShouldThrowException() {
        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> {
            userService.findByUuid("non-existent-uuid");
        });
    }
}