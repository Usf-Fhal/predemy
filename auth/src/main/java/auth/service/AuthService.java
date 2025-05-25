package auth.service;

import auth.dto.AuthenticationRequest;
import auth.dto.AuthenticationResponse;
import auth.dto.UserDto;
import auth.client.UserServiceClient;
import auth.config.JwtService;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(UserDto userDto) {
        var user = UserDto.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        
        // this only needed to get the role because the user service will assign a default role to the user
        //this will be removed in the next refactoring
        UserDto userdDto = userServiceClient.createUser(user).getBody();
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userdDto.getUuid());
        claims.put("email", userdDto.getEmail());
        claims.put("role", userdDto.getRole());



        var jwtToken = jwtService.generateToken(claims, userDto);
        // var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                // .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Validate credentials with UserService
        UserDto user = userServiceClient.authenticate(
            request.getEmail(), 
            request.getPassword()
        ).getBody();
        
        if (user == null) {
            throw new BadCredentialsException("Invalid credentials");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUuid());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());


        var jwtToken = jwtService.generateToken(claims, user);

        // Generate tokens
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                // .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

    //this will be uncommented when refresh token functionality is implemented
    // public AuthenticationResponse refreshToken(String refreshToken) {
    //     final String userEmail = jwtService.extractUsername(refreshToken);
    //     if (userEmail != null) {
    //         var user = userService.findByEmail(userEmail);
            
    //         if (jwtService.isTokenValid(refreshToken, user)) {
    //             var accessToken = jwtService.generateToken(user);
                
    //             return AuthenticationResponse.builder()
    //                     .accessToken(accessToken)
    //                     .refreshToken(refreshToken)
    //                     .build();
    //         }
    //     }
    //     throw new IllegalArgumentException("Invalid refresh token");
    // }
}
