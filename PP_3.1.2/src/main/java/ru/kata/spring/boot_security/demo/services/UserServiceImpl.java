package ru.kata.spring.boot_security.demo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.securities.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        String password = user.getPassword();
        String oldPassword = "";

        User existingUser = userRepository.getUser(user.getId());

        if (existingUser != null) {
            oldPassword = existingUser.getPassword();
        }

        if (!Objects.equals(password, oldPassword)) {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepository.saveUser(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Пользователь '%s' не зарегистрирован!", username));
        }

        return new UserDetailsImpl(user.get());
    }

    @Override
    @Transactional
    public void update(User user) {
        String password = user.getPassword();
        User existingUser = userRepository.getUser(user.getId());

        if (existingUser != null && !Objects.equals(password, existingUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepository.update(user);
    }
}
