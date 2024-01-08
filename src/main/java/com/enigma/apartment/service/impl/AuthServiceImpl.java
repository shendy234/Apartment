package com.enigma.apartment.service.impl;

import com.enigma.apartment.constant.ERole;
import com.enigma.apartment.dto.request.AuthRequest;
import com.enigma.apartment.dto.response.LoginResponse;
import com.enigma.apartment.dto.response.RegisterResponse;
import com.enigma.apartment.entity.Admin;
import com.enigma.apartment.entity.AppUser;
import com.enigma.apartment.entity.Role;
import com.enigma.apartment.entity.UserCredential;
import com.enigma.apartment.repository.UserCredentialRepository;
import com.enigma.apartment.security.JwtUtil;
import com.enigma.apartment.service.AdminService;
import com.enigma.apartment.service.AuthService;
import com.enigma.apartment.service.RoleService;
import com.enigma.apartment.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;
    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        try {
            validationUtil.validate(request);

            //TODO 1 : Set Role
            Role role = Role.builder()
                    .name(ERole.ROLE_ADMIN)
                    .build();
            role = roleService.getOrSave(role);
            //TODO 2: Set Credetial
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);
            //TODO 3 Set Admin
            Admin admin = Admin.builder()
                    .userCredential(userCredential)
                    .name(request.getUsername())
                    .phoneNumber(request.getMobilePhone())
                    .build();
            adminService.createNewAdmin(admin);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        }catch (DataIntegrityViolationException e){
            throw  new ResponseStatusException(HttpStatus.CONFLICT,"User already exist");
        }
    }

    @Override
    public RegisterResponse registerOwner(AuthRequest request) {
        try {
            validationUtil.validate(request);

            //TODO 1 : Set Role
            Role role = Role.builder()
                    .name(ERole.ROLE_OWNER)
                    .build();
            role = roleService.getOrSave(role);
            //TODO 2: Set Credetial
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);
            //TODO 3 Set Admin
            Admin admin = Admin.builder()
                    .userCredential(userCredential)
                    .name(request.getUsername())
                    .phoneNumber(request.getMobilePhone())
                    .build();
            adminService.createNewAdmin(admin);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        }catch (DataIntegrityViolationException e){
            throw  new ResponseStatusException(HttpStatus.CONFLICT,"User already exist");
        }
    }

    @Override
    public RegisterResponse registerTenant(AuthRequest request) {
        try {
            validationUtil.validate(request);

            //TODO 1 : Set Role
            Role role = Role.builder()
                    .name(ERole.ROLE_TENANT)
                    .build();
            role = roleService.getOrSave(role);
            //TODO 2: Set Credetial
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);
            //TODO 3 Set Admin
            Admin admin = Admin.builder()
                    .userCredential(userCredential)
                    .name(request.getUsername())
                    .phoneNumber(request.getMobilePhone())
                    .build();
            adminService.createNewAdmin(admin);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        }catch (DataIntegrityViolationException e){
            throw  new ResponseStatusException(HttpStatus.CONFLICT,"User already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        validationUtil.validate(authRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername().toLowerCase(),
                authRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //object appUser
        AppUser appUser = (AppUser)  authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);
        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}