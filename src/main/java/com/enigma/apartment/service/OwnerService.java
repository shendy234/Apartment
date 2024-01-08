package com.enigma.apartment.service;

import com.enigma.apartment.dto.request.OwnerRequest;
import com.enigma.apartment.dto.response.OwnerResponse;

import java.util.List;

public interface OwnerService {
    List<OwnerResponse> getAll();
    OwnerResponse create(OwnerRequest ownerRequest);
    OwnerResponse update(OwnerRequest ownerRequest);
    void delete(String id);
    OwnerResponse getById(String id);
}
