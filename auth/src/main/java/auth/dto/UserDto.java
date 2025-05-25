package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String uuid; // Unique identifier for the user
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role; //  role assigned to the user
    private String status; // User status (e.g., ACTIVE, INACTIVE, SUSPENDED)
}
// Additional fields can be added as needed