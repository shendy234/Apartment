package com.enigma.apartment.service.impl;

import com.enigma.apartment.dto.response.AdminResponse;
import com.enigma.apartment.entity.Admin;
import com.enigma.apartment.repository.AdminRepository;
import com.enigma.apartment.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public AdminResponse createNewAdmin(Admin request) {
        Admin admin = adminRepository.saveAndFlush(request);
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }
}
