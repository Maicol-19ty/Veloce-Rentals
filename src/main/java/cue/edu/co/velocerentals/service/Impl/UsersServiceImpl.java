package cue.edu.co.velocerentals.service.Impl;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.UsersCredentials;
import cue.edu.co.velocerentals.repository.UsersRepository;
import cue.edu.co.velocerentals.service.UsersService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;

// Implementation of UsersService.
@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    @Inject
    private UsersRepository<UsersDTo> usersRepository;

    // Method to register a user.
    @Override
    public void register(UsersDTo usersDTo, int roleId) {
        usersRepository.register(usersDTo, roleId);
    }

    // Method to check if a user exists by username or email.
    @Override
    public boolean checkUser(String username, String email) throws SQLException {
        return usersRepository.checkUser(username, email);
    }

    // Method to find a user and hash their password.
    @Override
    public UsersCredentials findUserAndHashPassword(String username) {
        return usersRepository.findUserAndHashPassword(username);
    }
}
