package com.enigma.apartment.service.impl;

import com.enigma.apartment.dto.request.ApartmentRequest;
import com.enigma.apartment.dto.response.ApartmentResponse;
import com.enigma.apartment.dto.response.OwnerResponse;
import com.enigma.apartment.entity.Apartment;
import com.enigma.apartment.entity.Owner;
import com.enigma.apartment.repository.ApartmentRepository;
import com.enigma.apartment.service.ApartmentService;
import com.enigma.apartment.service.OwnerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final OwnerService ownerService;

    @Override
    public List<ApartmentResponse> getAll() {
        List<Apartment> apartments = apartmentRepository.getAllApartment();
        return apartments.stream().map(apartment -> ApartmentResponse.builder()
                .id(apartment.getId())
                .addressApart(apartment.getAddressApart())
                .unitApart(apartment.getUnitApart())
                .price(apartment.getPrice())
                .description(apartment.getDescription())
                .ownerId(Owner.builder()
                        .id(apartment.getOwnerId().getId())
                        .fullName(apartment.getOwnerId().getFullName())
                        .phoneNumber(apartment.getOwnerId().getPhoneNumber())
                        .build())
                .build()).collect(Collectors.toList());
    }

    @Override
    public ApartmentResponse create(ApartmentRequest apartmentRequest) {
        OwnerResponse ownerResponse = ownerService.getById(apartmentRequest.getOwnerId().getId());
        Apartment apartment = Apartment.builder()
                .addressApart(apartmentRequest.getAddressApart())
                .unitApart(apartmentRequest.getUnitApart())
                .price(apartmentRequest.getPrice())
                .description(apartmentRequest.getDescription())
                .ownerId(Owner.builder()
                        .id(ownerResponse.getId())
                        .fullName(ownerResponse.getFullName())
                        .phoneNumber(ownerResponse.getPhoneNumber())
                        .build())
                .build();
        apartmentRepository.createApartment(apartmentRequest.getAddressApart(),apartmentRequest.getUnitApart(),apartmentRequest.getPrice(),apartmentRequest.getDescription(),true, ownerResponse.getId());
        return ApartmentResponse.builder()
                .id(apartment.getId())
                .addressApart(apartment.getAddressApart())
                .unitApart(apartment.getUnitApart())
                .price(apartment.getPrice())
                .description(apartment.getDescription())
                .isActive(true)
                .ownerId(Owner.builder()
                        .id(apartment.getOwnerId().getId())
                        .fullName(apartment.getOwnerId().getFullName())
                        .phoneNumber(apartment.getOwnerId().getPhoneNumber())
                        .build())
                .build();
    }

    @Override
    public ApartmentResponse update(ApartmentRequest apartmentRequest) {
        OwnerResponse ownerResponse = ownerService.getById(apartmentRequest.getOwnerId().getId());
        Apartment apartment = Apartment.builder()
                .id(apartmentRequest.getId())
                .addressApart(apartmentRequest.getAddressApart())
                .unitApart(apartmentRequest.getUnitApart())
                .price(apartmentRequest.getPrice())
                .description(apartmentRequest.getDescription())
                .ownerId(Owner.builder()
                        .id(ownerResponse.getId())
                        .fullName(ownerResponse.getFullName())
                        .phoneNumber(ownerResponse.getPhoneNumber())
                        .build())
                .build();
        apartmentRepository.updateApartment(apartmentRequest.getId(),apartmentRequest.getAddressApart(),apartmentRequest.getUnitApart(),apartmentRequest.getPrice(),apartmentRequest.getDescription(),true,ownerResponse.getId());
        return ApartmentResponse.builder()
                .id(apartment.getId())
                .addressApart(apartment.getAddressApart())
                .unitApart(apartment.getUnitApart())
                .price(apartment.getPrice())
                .description(apartment.getDescription())
                .isActive(true)
                .ownerId(Owner.builder()
                        .id(apartment.getOwnerId().getId())
                        .fullName(apartment.getOwnerId().getFullName())
                        .phoneNumber(apartment.getOwnerId().getPhoneNumber())
                        .build())
                .build();
    }

    @Override
    public void delete(String id) {
        apartmentRepository.findByIdApartment(id).orElseThrow(()-> new RuntimeException("Apartment doesn't exist"));
        apartmentRepository.deleteApartment(id);
    }

    @Override
    public ApartmentResponse getById(String id) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findByIdApartment(id);
        if (apartmentOptional.isPresent()) {
            Apartment apartment = apartmentOptional.get();
            return ApartmentResponse.builder()
                    .id(apartment.getId())
                    .addressApart(apartment.getAddressApart())
                    .unitApart(apartment.getUnitApart())
                    .price(apartment.getPrice())
                    .description(apartment.getDescription())
                    .ownerId(Owner.builder()
                            .id(apartment.getOwnerId().getId())
                            .fullName(apartment.getOwnerId().getFullName())
                            .phoneNumber(apartment.getOwnerId().getPhoneNumber())
                            .build())
                    .build();
        }else {
            return null;
        }
    }
}
