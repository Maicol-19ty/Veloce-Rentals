package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.UsersCredentials;

import java.sql.SQLException;

// Interface defining user service methods.
public interface UsersService {

    // Method to register a user.
    boolean register(UsersDTo usersDTo);

    // Method to check if a user exists by username or email.
    boolean checkUser(String username, String email) throws SQLException;

    // Method to find a user and hash their password.
    UsersCredentials findUserAndHashPassword(String username);
}
