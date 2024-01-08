package com.enigma.apartment.controller;

import com.enigma.apartment.constant.AppPath;
import com.enigma.apartment.dto.request.TenantRequest;
import com.enigma.apartment.dto.response.CommonResponse;
import com.enigma.apartment.dto.response.TenantResponse;
import com.enigma.apartment.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.TENANT)
public class TenantController {
    private final TenantService tenantService;

    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TenantRequest tenantRequest) {
        TenantResponse tenantResponse = tenantService.create(tenantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.<TenantResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully created Tenant")
                .data(tenantResponse)
                .build());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<TenantResponse> getAll() {
        return tenantService.getAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody TenantRequest tenantRequest) {
        TenantResponse tenantResponse = tenantService.update(tenantRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<TenantResponse>builder()
                        .message("Successfully update Tenant")
                        .data(tenantResponse)
                        .build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        TenantResponse tenantResponse = tenantService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<TenantResponse>builder()
                        .message("Successfully get Tenant by id.")
                        .data(tenantResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        tenantService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .message("Successfully delete Tanant.")
                        .data("OK")
                        .build());
    }
}
