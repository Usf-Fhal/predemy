package users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import users.services.UserService;
import users.dto.UserDto;
import users.entities.User;
import users.mapper.userMapper;


@RestController
@RequestMapping("/internal/users")
public class InternalUserController {

    @Value("${auth.service.secret}")
    private String authServiceSecret;

    private UserService userService;

    // (only accessible by Auth Service)
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(
            @RequestHeader("X-Auth-Service-Secret") String secret,
            @RequestBody UserDto userDto) {
        if (!authServiceSecret.equals(secret)) {
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userMapper.toEntity(userDto);
        userService.saveUser(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping("/validate-credentials")
    public ResponseEntity<UserDto> findUserByUsernameAndPassword(
        @RequestHeader("X-Auth-Service-Secret") String secret,
        @RequestParam String username,
        @RequestParam String password) {
        if (!authServiceSecret.equals(secret)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findByEmailAndPassword(username, password);
        UserDto userDto = userMapper.toDto(user);


        if (userDto == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/find-by-email")    
    public ResponseEntity<UserDto> findUserByEmail(
        @RequestHeader("X-Auth-Service-Secret") String secret,
        @RequestParam String email) {
        if (!authServiceSecret.equals(secret)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findByEmail(email);
        UserDto userDto = userMapper.toDto(user);
        
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userDto);
    }
}
