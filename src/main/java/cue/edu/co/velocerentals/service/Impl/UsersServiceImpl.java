// Implementation of the UsersService interface responsible for user-related operations.
// It utilizes the UsersRepository to interact with user data.
package cue.edu.co.velocerentals.service.Impl;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.Users;
import cue.edu.co.velocerentals.models.UsersCredentials;
import cue.edu.co.velocerentals.repository.UsersRepository;
import cue.edu.co.velocerentals.service.UsersService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.SQLException;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    @Inject
    private UsersRepository<UsersDTo> usersRepository;

    // Registers a new user with the provided details and role.
    @Override
    public void register(UsersDTo usersDTo, int roleId) {
        usersRepository.register(usersDTo, roleId);
    }

    // Checks if a user with the provided username or email already exists.
    @Override
    public boolean checkUser(String username, String email) throws SQLException {
        return usersRepository.checkUser(username, email);
    }

    // Retrieves user credentials (username and hashed password) based on the username.
    @Override
    public UsersCredentials findUserAndHashPassword(String username) {
        return usersRepository.findUserAndHashPassword(username);
    }

    // Retrieves user details based on the provided username.
    @Override
    public Users userDetails(String username) {
        return usersRepository.userDetails(username);
    }

    // Updates user profile information such as email and full name.
    @Override
    public boolean updateUserProfile(String username, String email, String fullName) {
        return usersRepository.updateUserProfile(username, email, fullName);
    }
}
