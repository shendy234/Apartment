package com.enigma.apartment.service.impl;

import com.enigma.apartment.dto.request.TenantRequest;
import com.enigma.apartment.dto.response.TenantResponse;
import com.enigma.apartment.entity.Tenant;
import com.enigma.apartment.repository.TenantRepository;
import com.enigma.apartment.service.TenantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;

    @Override
    public List<TenantResponse> getAll() {
        List<Tenant> tenants = tenantRepository.getAllTenants();
        return tenants.stream().map(tenant -> TenantResponse.builder()
                .id(tenant.getId())
                .fullName(tenant.getFullName())
                .phoneNumber(tenant.getPhoneNumber())
                .build()).collect(Collectors.toList());
    }
    @Override
    public TenantResponse create(TenantRequest tenantRequest) {
        Tenant tenant = Tenant.builder()
                .fullName(tenantRequest.getFullName())
                .phoneNumber(tenantRequest.getPhoneNumber())
                .build();
        tenantRepository.createTenant(tenantRequest.getFullName(), tenantRequest.getPhoneNumber());
        return TenantResponse.builder()
                .id(tenant.getId())
                .fullName(tenant.getFullName())
                .phoneNumber(tenant.getPhoneNumber())
                .build();
    }

    @Override
    public TenantResponse update(TenantRequest tenantRequest) {
        Tenant tenant = Tenant.builder()
                .id(tenantRequest.getId())
                .fullName(tenantRequest.getFullName())
                .phoneNumber(tenantRequest.getPhoneNumber())
                .build();
        tenantRepository.updateTenant(tenantRequest.getId(),tenantRequest.getFullName(),tenantRequest.getPhoneNumber());
        return TenantResponse.builder()
                .id(tenant.getId())
                .fullName(tenant.getFullName())
                .phoneNumber(tenant.getPhoneNumber())
                .build();
    }

    @Override
    public void delete(String id) {
        tenantRepository.findByIdTenant(id).orElseThrow(()-> new RuntimeException("Tenant doesn't exist"));
        tenantRepository.deleteTenant(id);
    }

    @Override
    public TenantResponse getById(String id) {
        Optional<Tenant> tenantOptional = tenantRepository.findByIdTenant(id);
        if (tenantOptional.isPresent()) {
            Tenant tenant = tenantOptional.get();
            return TenantResponse.builder()
                    .id(tenant.getId())
                    .fullName(tenant.getFullName())
                    .phoneNumber(tenant.getPhoneNumber())
                    .build();
        }else {
            return null;
        }
    }
}
