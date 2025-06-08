package com.atividade.prova_jwt.auth;

import com.atividade.prova_jwt.dto.AuthDTO;
import com.atividade.prova_jwt.dto.TokenDTO;
import com.atividade.prova_jwt.dto.UserDTO;
import com.atividade.prova_jwt.model.User;
import com.atividade.prova_jwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody AuthDTO.RegisterRequest request) {
        User createdUser = userService.register(request);

        UserDTO response = new UserDTO(
                createdUser.getUsername(),
                createdUser.getPassword(),
                createdUser.getRole()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthDTO.AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetails user = userService.loadUserByUsername(request.username());
        String token = jwtService.generateToken(user);

        TokenDTO response = new TokenDTO(token);
        return ResponseEntity.ok(response);
    }



}
