
package users.mapper;

import users.dto.UserDto;
import users.entities.User;

public class userMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null) return null;
        User user = new User( userDto.getFirstName(), 
                              userDto.getLastName(), 
                              userDto.getEmail(), 
                              userDto.getPassword() ); //  password already hashed in AuthService

        return user;
    }                   
}
