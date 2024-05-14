package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.Users;
import cue.edu.co.velocerentals.models.UsersCredentials;

import java.sql.SQLException;

public interface UsersService {

    void register(UsersDTo usersDTo, int roleId);
    boolean checkUser(String username, String email) throws SQLException;
    UsersCredentials findUserAndHashPassword(String username);
    Users userDetails(String username);
    boolean updateUserProfile(String username, String email, String fullName);
}
