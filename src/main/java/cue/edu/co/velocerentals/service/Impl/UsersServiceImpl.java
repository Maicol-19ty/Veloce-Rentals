package cue.edu.co.velocerentals.service.Impl;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;

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

    @Override
    public void register(UsersDTo usersDTo, int roleId) {
        usersRepository.register(usersDTo, roleId);
    }

    @Override
    public boolean checkUser(String username, String email) throws SQLException {
        return usersRepository.checkUser(username, email);

    }

    @Override
    public UsersCredentials findUserAndHashPassword(String username) {
        return usersRepository.findUserAndHashPassword(username);
    }
}
