package com.enigma.apartment.service;

import com.enigma.apartment.dto.request.AuthRequest;
import com.enigma.apartment.dto.response.LoginResponse;
import com.enigma.apartment.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerOwner(AuthRequest request);
    RegisterResponse registerTenant(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest authRequest);
}