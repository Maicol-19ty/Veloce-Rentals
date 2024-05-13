
package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.UsersCredentials;

import java.sql.SQLException;

public interface UsersService {

    // Method to register a new user
    void register(UsersDTo usersDTo, int roleId);

    // Method to check if a username or email already exists
    boolean checkUser(String username, String email) throws SQLException;

    // Method to find a user by username and hash the password
    UsersCredentials findUserAndHashPassword(String username);
}
