package com.enigma.apartment.controller;

import com.enigma.apartment.constant.AppPath;
import com.enigma.apartment.dto.request.ApartmentRequest;
import com.enigma.apartment.dto.request.OwnerRequest;
import com.enigma.apartment.dto.response.ApartmentResponse;
import com.enigma.apartment.dto.response.CommonResponse;
import com.enigma.apartment.dto.response.OwnerResponse;
import com.enigma.apartment.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.APARTMENT)
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ApartmentRequest apartmentRequest) {
        ApartmentResponse apartmentResponse = apartmentService.create(apartmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.<ApartmentResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully created Apartment")
                .data(apartmentResponse)
                .build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<ApartmentResponse> getAll() {
        return apartmentService.getAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ApartmentRequest apartmentRequest) {
        ApartmentResponse apartmentResponse = apartmentService.update(apartmentRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<ApartmentResponse>builder()
                        .message("Successfully update Apartment")
                        .data(apartmentResponse)
                        .build());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        ApartmentResponse apartmentResponse = apartmentService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<ApartmentResponse>builder()
                        .message("Successfully get Apartment by id.")
                        .data(apartmentResponse)
                        .build());
    }
    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        apartmentService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .message("Successfully delete Apartment.")
                        .data("OK")
                        .build());
    }
}
