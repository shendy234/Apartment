package com.enigma.apartment.controller;

import com.enigma.apartment.constant.AppPath;
import com.enigma.apartment.dto.request.ApartmentRequest;
import com.enigma.apartment.dto.request.BookingRequest;
import com.enigma.apartment.dto.response.ApartmentResponse;
import com.enigma.apartment.dto.response.BookingResponse;
import com.enigma.apartment.dto.response.CommonResponse;
import com.enigma.apartment.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BOOKING)
public class BookingController {
    private final BookingService bookingService;

    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookingRequest bookingRequest) {
        BookingResponse bookingResponse = bookingService.create(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.<BookingResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully created Booking")
                .data(bookingResponse)
                .build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<BookingResponse> getAll() {
        return bookingService.getAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookingRequest bookingRequest) {
        BookingResponse bookingResponse = bookingService.update(bookingRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<BookingResponse>builder()
                        .message("Successfully update Booking")
                        .data(bookingResponse)
                        .build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        BookingResponse bookingResponse = bookingService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<BookingResponse>builder()
                        .message("Successfully get Booking by id.")
                        .data(bookingResponse)
                        .build());
    }

    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        bookingService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .message("Successfully delete Booking.")
                        .data("OK")
                        .build());
    }
}
