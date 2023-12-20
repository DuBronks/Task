package ru.itmentor.spring.boot_security.demo.repository;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class HibernateUserRepository implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public Set<String> getUserRoles(long userId) {
        User user = getUserById(userId);
        if (user != null) {
            Set<String> roleNames = user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());
            return roleNames;
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public void addUserRole(long userId, String roleName) {
        User user = getUserById(userId);
        if (user != null) {
            Role role = new Role();
            role.setName(roleName);
            user.getRoles().add(role);
            entityManager.merge(user);
        }
    }

    @Override
    public void removeUserRole(long userId, String roleName) {
        User user = getUserById(userId);
        if (user != null) {
            Set<Role> rolesToRemove = user.getRoles()
                    .stream()
                    .filter(role -> role.getName().equals(roleName))
                    .collect(Collectors.toSet());

            user.getRoles().removeAll(rolesToRemove);
            entityManager.merge(user);
        }
    }
}
