package com.enigma.apartment.service;

import com.enigma.apartment.dto.request.BookingRequest;
import com.enigma.apartment.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    List<BookingResponse> getAll();
    BookingResponse create(BookingRequest bookingRequest);
    BookingResponse update(BookingRequest bookingRequest);
    void delete(String id);
    BookingResponse getById(String id);
}
