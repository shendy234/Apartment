package com.enigma.apartment.dto.response;

import com.enigma.apartment.entity.Apartment;
import com.enigma.apartment.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingResponse {
    private String id;
    private Date inDate;
    private Date outDate;
    private Apartment apartment;
    private Tenant tenant;
}
