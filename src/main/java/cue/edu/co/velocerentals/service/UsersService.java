package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.UsersCredentials;

import java.sql.SQLException;

public interface UsersService {

    boolean register(UsersDTo usersDTo);
    boolean checkUser(String username, String email) throws SQLException;
    UsersCredentials findUserAndHashPassword(String username);
}
