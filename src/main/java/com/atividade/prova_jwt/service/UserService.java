package com.atividade.prova_jwt.service;

import com.atividade.prova_jwt.dto.AdminUserUpdateDTO;
import com.atividade.prova_jwt.dto.AuthDTO;
import com.atividade.prova_jwt.dto.UserDTO;
import com.atividade.prova_jwt.dto.UserUpdateDTO;
import com.atividade.prova_jwt.model.User;
import com.atividade.prova_jwt.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User newUser = repository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Not Found!"));
        return newUser;
    }

    public User register(AuthDTO.RegisterRequest request) {

        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        return repository.save(newUser);
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()))
                .toList();
    }

    public UserDTO updateUser(Long id, @Valid UserUpdateDTO userDTO) {
        User userUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userUpdate.setUsername(userDTO.getUsername());
        userUpdate.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userUpdate.setEmail(userDTO.getEmail());

        User updatedUser = repository.save(userUpdate);
        return new UserDTO(updatedUser);
    }

    @Transactional
    public UserDTO updateUserByAdmin(Long id, AdminUserUpdateDTO adminUserDTO) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setUsername(adminUserDTO.getUsername());
        user.setEmail(adminUserDTO.getEmail());

        if (adminUserDTO.getPassword() != null && !adminUserDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(adminUserDTO.getPassword()));
        }

        user.setRole(adminUserDTO.getRole());

        User updatedUser = repository.save(user);
        return new UserDTO(updatedUser);
    }

    public UserDTO findById(Long id) {
        return repository.findById(id)
                .map(user -> new UserDTO(
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        repository.deleteById(id);
    }
}