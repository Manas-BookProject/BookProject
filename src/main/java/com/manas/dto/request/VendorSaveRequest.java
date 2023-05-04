package com.manas.dto.request;

public record VendorSaveRequest (
        String firstName,
        String lastName,
        String phoneNumber,
        String image,
        String description
){
}
