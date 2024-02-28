package com.jwtauthentication.jwtauth.auth;

import com.jwtauthentication.jwtauth.config.JwtService;
import com.jwtauthentication.jwtauth.repository.UserRepository;
import com.jwtauthentication.jwtauth.user.Role;
import com.jwtauthentication.jwtauth.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    JwtService jwtService;

    AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = new User();
        user.setFirstname(request.firstname);
        user.setLastname(request.lastname);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(Role.USER);

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        var authResponse = new AuthenticationResponse();
        authResponse.token = jwtToken;

        return authResponse;

    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email,
                        request.password
                )
        );

        var user = userRepository.findByEmail(request.email);

        var jwtToken = jwtService.generateToken(user.orElseThrow());

        var authResponse = new AuthenticationResponse();
        authResponse.token = jwtToken;

        return authResponse;
    }
}
