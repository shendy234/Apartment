package com.enigma.apartment.service;

import com.enigma.apartment.dto.request.OwnerRequest;
import com.enigma.apartment.dto.request.TenantRequest;
import com.enigma.apartment.dto.response.OwnerResponse;
import com.enigma.apartment.dto.response.TenantResponse;

import java.util.List;

public interface TenantService {
    List<TenantResponse> getAll();
    TenantResponse create(TenantRequest tenantRequest);
    TenantResponse update(TenantRequest tenantRequest);
    void delete(String id);
    TenantResponse getById(String id);
}
