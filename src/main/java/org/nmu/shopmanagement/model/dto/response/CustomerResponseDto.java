package org.nmu.shopmanagement.model.dto.response;

public record CustomerResponseDto (
    Long id,
    String name,
    String address,
    String city,
    String postalCode,
    String country,
    String phone) {
}
