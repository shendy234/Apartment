package com.enigma.apartment.controller;

import com.enigma.apartment.constant.AppPath;
import com.enigma.apartment.dto.request.OwnerRequest;
import com.enigma.apartment.dto.response.CommonResponse;
import com.enigma.apartment.dto.response.OwnerResponse;
import com.enigma.apartment.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.OWNER)
public class OwnerController {
    private final OwnerService ownerService;
    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OwnerRequest ownerRequest) {
        OwnerResponse ownerResponse = ownerService.create(ownerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.<OwnerResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully created Owner")
                .data(ownerResponse)
                .build());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<OwnerResponse> getAll() {
        return ownerService.getAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody OwnerRequest ownerRequest) {
        OwnerResponse ownerResponse = ownerService.update(ownerRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<OwnerResponse>builder()
                        .message("Successfully update Owner")
                        .data(ownerResponse)
                        .build());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        OwnerResponse ownerResponse = ownerService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<OwnerResponse>builder()
                        .message("Successfully get Owner by id.")
                        .data(ownerResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        ownerService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .message("Successfully delete Owner.")
                        .data("OK")
                        .build());
    }

}
