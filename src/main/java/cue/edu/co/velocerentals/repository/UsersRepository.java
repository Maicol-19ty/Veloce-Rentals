package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.models.UsersCredentials;

import java.sql.SQLException;

// Interface defining methods for user repository operations.
public interface UsersRepository<T> {

    // Method to register a user.
    boolean register(T t);

    // Method to check if a user exists by username and email.
    boolean checkUser(String username, String email) throws SQLException;

    // Method to find a user and hash their password.
    UsersCredentials findUserAndHashPassword(String username);
}
