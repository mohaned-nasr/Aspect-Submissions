package com.example.demo.service;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authMgr;
    private final PasswordEncoder encoder;
    private final UserRepository repo;
    private final JwtUtil jwt;

    public void register(RegisterRequest r) {
        repo.save(new User(r.getUsername(), encoder.encode(r.getPassword())));
    }

    public JwtResponse login(LoginRequest r) {
        Authentication auth = authMgr.authenticate(
                new UsernamePasswordAuthenticationToken(
                        r.getUsername(), r.getPassword()));

        return new JwtResponse(jwt.generateJwtToken(auth));
    }
}
