package com.enigma.apartment.controller;

import com.enigma.apartment.constant.AppPath;
import com.enigma.apartment.dto.request.AuthRequest;
import com.enigma.apartment.dto.response.CommonResponse;
import com.enigma.apartment.dto.response.LoginResponse;
import com.enigma.apartment.dto.response.RegisterResponse;
import com.enigma.apartment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping(AppPath.AUTH)
    public class AuthController {
        private final AuthService authService;

        @PostMapping("/admin")
        public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest request) {
            RegisterResponse registerResponse= authService.registerAdmin(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .message("Successfully Regis")
                            .data(registerResponse)
                            .build());
        }

        @PostMapping("/owner")
        public ResponseEntity<?> registerOwner(@RequestBody AuthRequest request) {
            RegisterResponse registerResponse= authService.registerOwner(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .message("Successfully Regis")
                            .data(registerResponse)
                            .build());
        }

        @PostMapping("/tenant")
        public ResponseEntity<?> registerTenant(@RequestBody AuthRequest request) {
            RegisterResponse registerResponse= authService.registerTenant(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .message("Successfully Regis")
                            .data(registerResponse)
                            .build());
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
            LoginResponse loginResponse = authService.login(authRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Successfully Login")
                            .data(loginResponse)
                            .build());
        }
    }
