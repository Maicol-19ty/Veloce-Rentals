package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.models.UsersCredentials;

import java.sql.SQLException;

public interface UsersRepository <T> {

    void register(T t, int roleId);
    boolean checkUser(String username, String email) throws SQLException;
    UsersCredentials findUserAndHashPassword(String username);
}
