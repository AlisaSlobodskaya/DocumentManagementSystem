package com.application.service;

import com.application.entity.User;
import com.application.entity.enumeration.UserRole;
import com.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setRole(roleService.getRoleByName(UserRole.CLIENT));
        System.out.println(user.getRole().toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean updateUser(User user) {
        if (user == null) {
            return false;
        }
        userRepository.updateUser(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getId(),
                user.getId()
        );
        return true;
    }

    public boolean deleteUser(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
