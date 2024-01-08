package com.enigma.apartment.service.impl;

import com.enigma.apartment.dto.request.BookingRequest;
import com.enigma.apartment.dto.response.ApartmentResponse;
import com.enigma.apartment.dto.response.BookingResponse;
import com.enigma.apartment.dto.response.TenantResponse;
import com.enigma.apartment.entity.Apartment;
import com.enigma.apartment.entity.Booking;
import com.enigma.apartment.entity.Owner;
import com.enigma.apartment.entity.Tenant;
import com.enigma.apartment.repository.ApartmentRepository;
import com.enigma.apartment.repository.BookingRepository;
import com.enigma.apartment.service.ApartmentService;
import com.enigma.apartment.service.BookingService;
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
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final TenantService tenantService;
    private final ApartmentService apartmentService;
    private final ApartmentRepository apartmentRepository;

    @Override
    public List<BookingResponse> getAll() {
        List<Booking> bookings = bookingRepository.getAllBooking();
        return bookings.stream().map(booking -> BookingResponse.builder()
                .id(booking.getId())
                .inDate(booking.getInDate())
                .outDate(booking.getOutDate())
                .tenant(Tenant.builder()
                        .id(booking.getTenant().getId())
                        .fullName(booking.getTenant().getFullName())
                        .phoneNumber(booking.getTenant().getPhoneNumber())
                        .build())
                .apartment(Apartment.builder()
                        .id(booking.getApartment().getId())
                        .addressApart(booking.getApartment().getAddressApart())
                        .unitApart(booking.getApartment().getUnitApart())
                        .price(booking.getApartment().getPrice())
                        .isActive(true)
                        .ownerId(Owner.builder()
                                .id(booking.getApartment().getOwnerId().getId())
                                .fullName(booking.getApartment().getOwnerId().getFullName())
                                .phoneNumber(booking.getApartment().getOwnerId().getPhoneNumber())
                                .build())
                        .build())
                .build()).collect(Collectors.toList());
    }

    @Override
    public BookingResponse create(BookingRequest bookingRequest) {
            TenantResponse tenantResponse = tenantService.getById(bookingRequest.getTenant().getId());
        ApartmentResponse apartmentResponse = apartmentService.getById(bookingRequest.getApartment().getId());
        Booking booking = Booking.builder()
                .id(bookingRequest.getId())
                .inDate(bookingRequest.getInDate())
                .outDate(bookingRequest.getOutDate())
                .tenant(Tenant.builder()
                        .id(tenantResponse.getId())
                        .fullName(tenantResponse.getFullName())
                        .phoneNumber(tenantResponse.getPhoneNumber())
                        .build())
                .apartment(Apartment.builder()
                        .id(apartmentResponse.getId())
                        .addressApart(apartmentResponse.getAddressApart())
                        .unitApart(apartmentResponse.getUnitApart())
                        .price(apartmentResponse.getPrice())
                        .isActive(true)
                        .ownerId(Owner.builder()
                                .id(apartmentResponse.getOwnerId().getId())
                                .fullName(apartmentResponse.getOwnerId().getFullName())
                                .phoneNumber(apartmentResponse.getOwnerId().getPhoneNumber())
                                .build())
                        .build())
                .build();
        bookingRepository.createBooking(bookingRequest.getInDate(), bookingRequest.getOutDate(), apartmentResponse.getId(), tenantResponse.getId());
        return BookingResponse.builder()
                .id(booking.getId())
                .inDate(booking.getInDate())
                .outDate(booking.getOutDate())
                .tenant(Tenant.builder()
                        .id(booking.getTenant().getId())
                        .fullName(booking.getTenant().getFullName())
                        .phoneNumber(booking.getTenant().getPhoneNumber())
                        .build())
                .apartment(Apartment.builder()
                        .id(booking.getApartment().getId())
                        .addressApart(booking.getApartment().getAddressApart())
                        .unitApart(booking.getApartment().getUnitApart())
                        .price(booking.getApartment().getPrice())
                        .isActive(true)
                        .ownerId(Owner.builder()
                                .id(booking.getApartment().getOwnerId().getId())
                                .fullName(booking.getApartment().getOwnerId().getFullName())
                                .phoneNumber(booking.getApartment().getOwnerId().getPhoneNumber())
                                .build())
                        .build())
                .build();
    }

    @Override
    public BookingResponse update(BookingRequest bookingRequest) {
        TenantResponse tenantResponse = tenantService.getById(bookingRequest.getTenant().getId());
        ApartmentResponse apartmentResponse = apartmentService.getById(bookingRequest.getApartment().getId());
        Booking booking = Booking.builder()
                .id(bookingRequest.getId())
                .inDate(bookingRequest.getInDate())
                .outDate(bookingRequest.getOutDate())
                .tenant(Tenant.builder()
                        .id(tenantResponse.getId())
                        .fullName(tenantResponse.getFullName())
                        .phoneNumber(tenantResponse.getPhoneNumber())
                        .build())
                .apartment(Apartment.builder()
                        .id(apartmentResponse.getId())
                        .addressApart(apartmentResponse.getAddressApart())
                        .unitApart(apartmentResponse.getUnitApart())
                        .price(apartmentResponse.getPrice())
                        .isActive(true)
                        .ownerId(Owner.builder()
                                .id(apartmentResponse.getOwnerId().getId())
                                .fullName(apartmentResponse.getOwnerId().getFullName())
                                .phoneNumber(apartmentResponse.getOwnerId().getPhoneNumber())
                                .build())
                        .build())
                .build();
        bookingRepository.updateBooking(bookingRequest.getInDate(), bookingRequest.getOutDate(), apartmentResponse.getId(), tenantResponse.getId());
        return BookingResponse.builder()
                .id(booking.getId())
                .inDate(booking.getInDate())
                .outDate(booking.getOutDate())
                .tenant(Tenant.builder()
                        .id(booking.getTenant().getId())
                        .fullName(booking.getTenant().getFullName())
                        .phoneNumber(booking.getTenant().getPhoneNumber())
                        .build())
                .apartment(Apartment.builder()
                        .id(booking.getApartment().getId())
                        .addressApart(booking.getApartment().getAddressApart())
                        .unitApart(booking.getApartment().getUnitApart())
                        .price(booking.getApartment().getPrice())
                        .isActive(true)
                        .ownerId(Owner.builder()
                                .id(booking.getApartment().getOwnerId().getId())
                                .fullName(booking.getApartment().getOwnerId().getFullName())
                                .phoneNumber(booking.getApartment().getOwnerId().getPhoneNumber())
                                .build())
                        .build())
                .build();
    }

    @Override
    public void delete(String id) {
        bookingRepository.findByIdBooking(id).orElseThrow(() -> new RuntimeException("Booking doesn't exist"));
        bookingRepository.deleteBooking(id);
    }

    @Override
    public BookingResponse getById(String id) {
        Optional<Booking> bookingOptional = bookingRepository.findByIdBooking(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            return BookingResponse.builder()
                    .id(booking.getId())
                    .inDate(booking.getInDate())
                    .outDate(booking.getOutDate())
                    .tenant(Tenant.builder()
                            .id(booking.getTenant().getId())
                            .fullName(booking.getTenant().getFullName())
                            .phoneNumber(booking.getTenant().getPhoneNumber())
                            .build())
                    .apartment(Apartment.builder()
                            .id(booking.getApartment().getId())
                            .addressApart(booking.getApartment().getAddressApart())
                            .unitApart(booking.getApartment().getUnitApart())
                            .price(booking.getApartment().getPrice())
                            .isActive(true)
                            .ownerId(Owner.builder()
                                    .id(booking.getApartment().getOwnerId().getId())
                                    .fullName(booking.getApartment().getOwnerId().getFullName())
                                    .phoneNumber(booking.getApartment().getOwnerId().getPhoneNumber())
                                    .build())
                            .build())
                    .build();
        } else {
            return null;
        }
    }
}
