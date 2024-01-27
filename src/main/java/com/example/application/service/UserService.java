package com.example.application.service;

import com.example.application.entity.User;
import com.example.application.exception.BadCredentialsException;
import com.example.application.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public void registerUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean auth(String email, String password) {
        System.out.println("run!\n\n");
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new BadCredentialsException("user not found with this email!: "+email);

        try {
            System.out.println("Attempting authentication...");

//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    email,
//                    password));
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    email,
                    password));

            // Set the authentication result into the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Successfully authenticated.");
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed!");
            e.printStackTrace();
            throw new BadCredentialsException("Bad credentials");
        }

        System.out.println("return true!\n\n");

        return true;

    }
}
