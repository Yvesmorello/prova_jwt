package com.atividade.prova_jwt.controller;

import com.atividade.prova_jwt.dto.AdminUserUpdateDTO;
import com.atividade.prova_jwt.dto.UserDTO;
import com.atividade.prova_jwt.dto.UserUpdateDTO;
import com.atividade.prova_jwt.model.User;
import com.atividade.prova_jwt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyProfile(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        UserDTO user = userService.findById(currentUser.getId());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateMyProfile(
            @Valid @RequestBody UserUpdateDTO userDTO,
            Authentication authentication) {

        User currentUser = (User) authentication.getPrincipal();
        UserDTO updatedUser = userService.updateUser(currentUser.getId(), userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> updateUserByAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminUserUpdateDTO adminUserDTO) {

        UserDTO updatedUser = userService.updateUserByAdmin(id, adminUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
