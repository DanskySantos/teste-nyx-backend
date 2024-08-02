package com.nyx.nyx_backend.security.user.resource;

import com.nyx.nyx_backend.security.user.entities.User;
import com.nyx.nyx_backend.security.user.entities.request.UserRequest;
import com.nyx.nyx_backend.security.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAllUser();
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getByEmail(HttpServletRequest request) {
        return ResponseEntity.ok(userService.findByTokenEmail(request.getHeader("Authorization")));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUser) {
        if (id == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encotrado com esse id");

        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if(id == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encotrado com esse id");

        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
