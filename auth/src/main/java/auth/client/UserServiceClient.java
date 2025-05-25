package auth.client;

import auth.dto.UserDto;
import org.springframework.http.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * Note: Resilience mechanisms (such as retries or circuit breakers) will be added later to handle failed requests.
 * </p>
 */
@FeignClient(
    name = "user-service",
    configuration = FeignConfig.class
)
public interface UserServiceClient {

    @PostMapping("/internal/users/register")
    ResponseEntity<UserDto> createUser( @RequestBody UserDto userDto  );

    @PostMapping("/internal/users/validate-credentials")
    ResponseEntity<UserDto> authenticate(
        @RequestParam String username, 
        @RequestParam String password);

    @PostMapping("/internal/users/find-by-email")
    ResponseEntity<UserDto> findByEmail(
        @RequestParam String email);
}
