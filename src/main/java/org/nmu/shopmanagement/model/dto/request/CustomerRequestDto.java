package org.nmu.shopmanagement.model.dto.request;


public record CustomerRequestDto (
    String name,
    String address,
    String city,
    String postalCode,
    String country,
    String phone) {}
