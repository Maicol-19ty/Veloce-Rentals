
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

import java.sql.*;

@ApplicationScoped
public class UsersRepositoryImpl implements UsersRepository<UsersDTo> {

    @Inject
    @MySqlConn
    private Connection conn;

    // Method to register a new user in the database.
    @Override
    public void register(UsersDTo usersDTo, int roleId) {

        PreparedStatement stmt = null;

        try {
            conn = DataBaseConnection.getInstance();
            conn.setAutoCommit(false);

            // SQL query to insert user data into the 'users' table.
            String sqlInsertUser = "INSERT INTO users (username, password, email, full_name) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sqlInsertUser, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usersDTo.username());
            String hashedPassword = HashingUtil.hashPassword(usersDTo.password());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, usersDTo.email());
            stmt.setString(4, usersDTo.full_name());
            int userInserted = stmt.executeUpdate();

            // If user insertion fails, rollback the transaction.
            if (userInserted == 0) {
                conn.rollback();
                return;
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (!generatedKeys.next()) {
                conn.rollback();
                return;
            }
            int userId = generatedKeys.getInt(1);

            // SQL query to insert user role into the 'user_roles' table.
            String sqlInsertUserRole = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(sqlInsertUserRole);
            stmt.setInt(1, userId);
            stmt.setInt(2, roleId);
            stmt.executeUpdate();

            // Commit the transaction after successful registration.
            conn.commit();
        } catch (SQLException | ServiceJdbcException e) {
            // Rollback transaction if any exception occurs during registration.
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Close the prepared statement and reset auto-commit mode.
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
        }
    }

    // Method to check if a user with the given username or email already exists.
    @Override
    public boolean checkUser(String username, String email) throws SQLException {
        try {
            conn = DataBaseConnection.getInstance();
            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ? OR email = ?");
            statement.setString(1, username);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException | ServiceJdbcException e) {
            // Throw SQLException if an error occurs during user existence check.
            throw new SQLException("Error checking if username exists.", e);
        }
        return false;
    }

    // Method to find a user by username and retrieve hashed password and role name.
    @Override
    public UsersCredentials findUserAndHashPassword(String username) {

        UsersCredentials usersCredentials = null;
        try {
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
