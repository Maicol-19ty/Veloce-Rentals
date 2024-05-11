package cue.edu.co.velocerentals.repository.Impl;

import cue.edu.co.velocerentals.annotations.MySqlConn;
import cue.edu.co.velocerentals.database.DataBaseConnection;
import cue.edu.co.velocerentals.exceptions.ServiceJdbcException;
import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.UsersCredentials;
import cue.edu.co.velocerentals.repository.UsersRepository;
import cue.edu.co.velocerentals.utils.HashingUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Implementation of UsersRepository for MySQL database.
@ApplicationScoped
public class UsersRepositoryImpl implements UsersRepository<UsersDTo> {

    // Injected MySQL connection.
    @Inject
    @MySqlConn
    private Connection conn;

    // Method to register a user.
    @Override
    public boolean register(UsersDTo usersDTo) {
        try {
            // Get connection from the connection pool.
            conn = DataBaseConnection.getInstance();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO users (username, password, email, full_name) VALUES (?, ?, ?, ?)");
            statement.setString(1, usersDTo.username());

            // Hash the password before storing.
            String hashedPassword = HashingUtil.hashPassword(usersDTo.password());
            statement.setString(2, hashedPassword);
            statement.setString(3, usersDTo.email());
            statement.setString(4, usersDTo.full_name());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException | ServiceJdbcException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to check if a user with given username or email already exists.
    @Override
    public boolean checkUser(String username, String email) throws SQLException {
        try {
            // Get connection from the connection pool.
            conn = DataBaseConnection.getInstance();
            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ? OR email = ?");
            statement.setString(1, username);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException | ServiceJdbcException e) {
            throw new SQLException("Error checking if username exists.", e);
        }
        return false;
    }

    // Method to find user and retrieve hashed password along with role name.
    @Override
    public UsersCredentials findUserAndHashPassword(String username) {
        UsersCredentials usersCredentials = null;
        try {
            // Get connection from the connection pool.
            conn = DataBaseConnection.getInstance();
            String sql = "SELECT u.password, r.role_name FROM users u " +
                    "JOIN user_roles ur ON u.user_id = ur.user_id " +
                    "JOIN roles r ON ur.role_id = r.role_id " +
                    "WHERE u.username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String roleName = resultSet.getString("role_name");
                usersCredentials = new UsersCredentials(username, password, roleName);
            }
        } catch (SQLException | ServiceJdbcException e) {
            e.printStackTrace();
        }
        return usersCredentials;
    }
}
