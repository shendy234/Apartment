package com.enigma.apartment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OwnerResponse {
    private String id;
    private String fullName;
    private String phoneNumber;
}