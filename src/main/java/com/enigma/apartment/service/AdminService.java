package com.enigma.apartment.service;

import com.enigma.apartment.dto.response.AdminResponse;
import com.enigma.apartment.entity.Admin;

public interface AdminService {
    AdminResponse createNewAdmin(Admin request);
}

