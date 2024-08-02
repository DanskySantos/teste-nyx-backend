package com.nyx.nyx_backend.security.user.resource;

import com.nyx.nyx_backend.security.user.entities.request.AuthenticationRequest;
import com.nyx.nyx_backend.security.user.entities.request.RegisterRequest;
import com.nyx.nyx_backend.security.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthLoginController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        var authenticationResponse = userService.authenticate(request);
        if (userService.findByEmail(request.getEmail()).isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe um usuário com esse e-mail");

        if (authenticationResponse.getStatusCode() == HttpStatus.BAD_REQUEST)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha Incorreta");

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (userService.findByEmail(registerRequest.getEmail()).isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário cadastrado com esse e-mail");

        return ResponseEntity.ok(userService.register(registerRequest));
    }
}
