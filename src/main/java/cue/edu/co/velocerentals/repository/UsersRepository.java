package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.models.Users;
import cue.edu.co.velocerentals.models.UsersCredentials;

import java.sql.SQLException;

public interface UsersRepository<T> {

    // Method to register a new user with a specified role ID
    void register(T t, int roleId);

    // Method to check if a user with the given username or email already exists
    boolean checkUser(String username, String email) throws SQLException;

    // Method to find a user by username and retrieve hashed password along with role information
    UsersCredentials findUserAndHashPassword(String username);
    Users userDetails(String username);
    boolean updateUserProfile(String username, String email, String fullName);
}
