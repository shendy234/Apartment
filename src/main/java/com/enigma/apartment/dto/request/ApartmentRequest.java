package com.enigma.apartment.dto.request;

import com.enigma.apartment.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ApartmentRequest {
    private String id;
    private String addressApart;
    private Integer unitApart;
    private Long price;
    private String description;
    private Boolean isActive;
    private Owner ownerId;
}
