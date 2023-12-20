package ru.itmentor.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;


import java.util.List;
import java.util.Set;

@Repository
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;

    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return repository.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        repository.addUser(user);
    }

    @Override
    public void removeUser(long id) {
        repository.removeUser(id);
    }

    @Override
    public void updateUser(User user) {
        repository.updateUser(user);
    }

    @Override
    public Set<String> getUserRoles(long userId) {
        return repository.getUserRoles(userId);
    }

    @Override
    public void addUserRole(long userId, String role) {
        repository.addUserRole(userId, role);
    }

    @Override
    public void removeUserRole(long userId, String role) {
        repository.removeUserRole(userId, role);
    }
}
