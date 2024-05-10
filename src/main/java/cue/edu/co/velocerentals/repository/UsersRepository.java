package cue.edu.co.velocerentals.repository;

import java.sql.SQLException;

public interface UsersRepository<T> {

    boolean register(T t);
    boolean checkUser(String username, String email) throws SQLException;
    String findUserAndHashPassword(String username);
}
