package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.Users;

// Mapper class for converting between Users and UsersDTo objects.
public class UsersMapper {

    // Method to map from Users model to UsersDTo DTO.
    public static UsersDTo mapFromModel(Users users) {
        return new UsersDTo(users.getUser_id(), users.getUsername(), users.getPassword(), users.getEmail(),
                users.getFull_name(), users.getCreated_at(), users.getLast_login());
    }

    // Method to map from UsersDTo DTO to Users model.
    public static Users mapFromDTO(UsersDTo usersDTo) {
        return Users.builder()
                .user_id(usersDTo.user_id())
                .username(usersDTo.username())
                .password(usersDTo.password())
                .email(usersDTo.email())
                .full_name(usersDTo.full_name())
                .created_at(usersDTo.created_at())
                .last_login(usersDTo.last_login())
                .build();
    }

}
