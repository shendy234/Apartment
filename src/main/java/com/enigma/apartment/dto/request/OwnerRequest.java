package com.enigma.apartment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OwnerRequest {
    private String id;
    private String fullName;
    private String phoneNumber;
}
