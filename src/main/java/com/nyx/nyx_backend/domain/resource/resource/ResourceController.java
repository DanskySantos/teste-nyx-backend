package com.nyx.nyx_backend.domain.resource.resource;

import com.nyx.nyx_backend.domain.resource.service.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/findByMesMovimentacao")
    public ResponseEntity<?> findByMesMovimentacao(HttpServletRequest request) {
        if (request.getHeader("Authorization") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado");

        return ResponseEntity.ok(resourceService.findByMesMovimentacao());
    }

    @GetMapping("/findByCategoria")
    public ResponseEntity<?> findByCategoria(HttpServletRequest request) {
        if (request.getHeader("Authorization") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado");

        return ResponseEntity.ok(resourceService.findByCategoria());
    }

    @GetMapping("/findByFonte")
    public ResponseEntity<?> findByFonte(HttpServletRequest request) {
        if (request.getHeader("Authorization") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado");

        return ResponseEntity.ok(resourceService.findByFonte());
    }
}

