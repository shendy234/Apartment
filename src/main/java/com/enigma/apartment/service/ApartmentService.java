package com.enigma.apartment.service;

import com.enigma.apartment.dto.request.ApartmentRequest;
import com.enigma.apartment.dto.response.ApartmentResponse;

import java.util.List;

public interface ApartmentService {
    List<ApartmentResponse> getAll();
    ApartmentResponse create(ApartmentRequest apartmentRequest);
    ApartmentResponse update(ApartmentRequest apartmentRequest);
    void delete(String id);
    ApartmentResponse getById(String id);
}
