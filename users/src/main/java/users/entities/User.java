package users.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * Represents a User entity with various attributes such as personal details,
 * contact information, role, and status.
 * 
 * <p>
 * The `status` field will be set to active by default. In the future, a new
 * mailing service will be introduced, requiring users to verify their accounts
 * via email before the account can be marked as active.
 * </p>
 * 
 * <p>
 * Note: The `password` field is currently stored as a plain string. This will
 * be updated in the future to use a more secure approach, such as hashing, to
 * enhance security.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(255)")
    private String uuid; // Unique identifier

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Enum for user roles (default: USER)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; // Enum for user status (default: ACTIVE)

    @CreationTimestamp
    private LocalDateTime createdAt; // Automatically set when the entity is created

    @UpdateTimestamp
    private LocalDateTime updatedAt; // Automatically updated when the entity is modified

    // Custom constructor excluding ORM-managed fields and setting default values
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = Role.STUDENT; // Default role
        this.status = Status.ACTIVE; // Default status
    }

    // Additional fields can be added as needed
    // private String phoneNumber;
    // private String address;
    // private String dateOfBirth;
    // private String lastLogin;
    // private String lastLoginIp;
}