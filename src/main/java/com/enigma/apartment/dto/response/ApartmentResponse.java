package com.enigma.apartment.dto.response;

import com.enigma.apartment.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ApartmentResponse {
    private String id;
    private String addressApart;
    private Integer unitApart;
    private Long price;
    private String description;
    private Boolean isActive;
    private Owner ownerId;
}
