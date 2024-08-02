package com.nyx.nyx_backend.security.user.service;

import com.nyx.nyx_backend.security.person.entities.Person;
import com.nyx.nyx_backend.security.person.repository.PersonRepository;
import com.nyx.nyx_backend.security.service.JwtService;
import com.nyx.nyx_backend.security.token.entity.Token;
import com.nyx.nyx_backend.security.token.repository.TokenRepository;
import com.nyx.nyx_backend.security.token.tokenEnum.TokenType;
import com.nyx.nyx_backend.security.user.entities.User;
import com.nyx.nyx_backend.security.user.entities.request.AuthenticationRequest;
import com.nyx.nyx_backend.security.user.entities.request.RegisterRequest;
import com.nyx.nyx_backend.security.user.entities.request.UserRequest;
import com.nyx.nyx_backend.security.user.entities.response.AuthenticationResponse;
import com.nyx.nyx_backend.security.user.entities.response.UserResponse;
import com.nyx.nyx_backend.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        createUsername(registerRequest);
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());
        registerRequest.setPassword(encryptedPassword);
        Long remainingDays = 0L;

        User newUser = User.createUser(registerRequest);
        Person newPerson = Person.createPerson(registerRequest);

        Person personSaved = personRepository.save(newPerson);
        User userSaved = userRepository.save(newUser);

        personSaved.setUserId(userSaved.getId());
        userSaved.setPerson(personSaved);

        var jwtToken = jwtService.generateTokenWithRole(userSaved);
        var refreshToken = jwtService.generateRefreshToken(userSaved);
        saveUserToken(userSaved, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .userId(userSaved.getId())
                .userName(userSaved.getUsernameForDto())
                .userRole(String.valueOf(userSaved.getRole()))
                .refreshToken(refreshToken)
                .build();
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest request) {
        try {
            var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha Incorreta");
            }

            var user = userRepository.findByEmailAndExcludedFalse(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateTokenWithRole(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);

            return ResponseEntity.ok().body(
                    AuthenticationResponse.builder()
                            .accessToken(jwtToken)
                            .userId(user.getId())
                            .refreshToken(refreshToken)
                            .userName(user.getUsernameForDto())
                            .userRole(String.valueOf(user.getRole()))
                            .build()
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha Incorreta");
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getId()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailAndExcludedFalse(email);
    }

    public UserResponse findByTokenEmail(String token) {
        var user = userRepository.findByEmailAndExcludedFalse(jwtService.getEmail(token)).get();

        return UserResponse.mapEntityToResponse(user);
    }

    public Optional<User> findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndExcludedFalse(username);
    }

    public User updateUser(Long id, UserRequest updateUser) {
        User user = userRepository.findById(id).get();

        return userRepository.save(User.updateUser(user, updateUser));
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailAndExcludedFalse(email).get();
    }

    public void createUsername(RegisterRequest registerRequest) {
        String[] nameParts = registerRequest.getName().split("\\s+");
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";
        String capitalizedFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        String capitalizedLastName = lastName.length() > 0 ? lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase() : "";
        String username = capitalizedFirstName + " " + capitalizedLastName;
        registerRequest.setUsername(username);
    }

    public User deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        return userRepository.save(User.deleteUser(user));
    }
}
