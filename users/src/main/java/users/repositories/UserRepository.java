package users.repositories;

import users.entities.Status;
import users.entities.User;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users", itemResourceRel = "user")
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email); // Find a user by their email address

    List<User> findByRole(Role role); // List a users by their role    

    Optional<User> findByUsernameAndPassword(String username, String password); // Find a user by username and password

    List<User> findByStatus(Status status); // List users by their status

    boolean existsByEmail(String email); // Check if a user exists by their email address

}