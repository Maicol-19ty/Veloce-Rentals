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

    @Override
    public void register(UsersDTo usersDTo, int roleId) {

        PreparedStatement stmt = null;

        try {
            conn = DataBaseConnection.getInstance();
            conn.setAutoCommit(false);

            String sqlInsertUser = "INSERT INTO users (username, password, email, full_name) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sqlInsertUser, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usersDTo.username());
            String hashedPassword = HashingUtil.hashPassword(usersDTo.password());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, usersDTo.email());
            stmt.setString(4, usersDTo.full_name());
            int userInserted = stmt.executeUpdate();

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

            String sqlInsertUserRole = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(sqlInsertUserRole);
            stmt.setInt(1, userId);
            stmt.setInt(2, roleId);
            stmt.executeUpdate();

            conn.commit();
        } catch (SQLException | ServiceJdbcException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
        }
    }
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
            throw new SQLException("Error checking if username exists.", e);
        }
        return false;
    }

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
