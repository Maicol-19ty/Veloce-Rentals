package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;

import java.sql.SQLException;

public interface UsersService {

    boolean register(UsersDTo usersDTo);
    boolean checkUser(String username, String email) throws SQLException;
    String findUserAndHashPassword(String username);
}
