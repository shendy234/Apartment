package com.enigma.apartment.service.impl;

import com.enigma.apartment.dto.request.OwnerRequest;
import com.enigma.apartment.dto.response.OwnerResponse;
import com.enigma.apartment.entity.Owner;
import com.enigma.apartment.repository.OwnerRepository;
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
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public List<OwnerResponse> getAll() {
        List<Owner> owners = ownerRepository.getAllOwners();
        return owners.stream().map(owner -> OwnerResponse.builder()
                .id(owner.getId())
                .fullName(owner.getFullName())
                .phoneNumber(owner.getPhoneNumber())
                .build()).collect(Collectors.toList());
    }
    @Override
    public OwnerResponse create(OwnerRequest ownerRequest) {
        Owner owner = Owner.builder()
                .fullName(ownerRequest.getFullName())
                .phoneNumber(ownerRequest.getPhoneNumber())
                .build();
        ownerRepository.createOwner(ownerRequest.getFullName(), ownerRequest.getPhoneNumber());
        return OwnerResponse.builder()
                .id(owner.getId())
                .fullName(owner.getFullName())
                .phoneNumber(owner.getPhoneNumber())
                .build();
    }
    @Override
    public OwnerResponse update(OwnerRequest ownerRequest) {
        Owner owner = Owner.builder()
                .id(ownerRequest.getId())
                .fullName(ownerRequest.getFullName())
                .phoneNumber(ownerRequest.getPhoneNumber())
                .build();
        ownerRepository.updateOwner(ownerRequest.getId(),ownerRequest.getFullName(),ownerRequest.getPhoneNumber());
        return OwnerResponse.builder()
                .id(owner.getId())
                .fullName(owner.getFullName())
                .phoneNumber(owner.getPhoneNumber())
                .build();
    }

    @Override
    public void delete(String id) {
            ownerRepository.findByIdOwner(id).orElseThrow(()-> new RuntimeException("owner doesn't exist"));
            ownerRepository.deleteOwner(id);
    }

    @Override
    public OwnerResponse getById(String id) {
        Optional<Owner> ownerOptional = ownerRepository.findByIdOwner(id);
        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            return OwnerResponse.builder()
                    .id(owner.getId())
                    .fullName(owner.getFullName())
                    .phoneNumber(owner.getPhoneNumber())
                    .build();
        }else {
            return null;
        }
    }
}
