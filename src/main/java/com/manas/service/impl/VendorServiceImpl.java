package com.manas.service.impl;

import com.manas.dto.request.VendorSaveRequest;
import com.manas.dto.response.SimpleResponse;
import com.manas.entity.Vendor;
import com.manas.repository.VendorRepository;
import com.manas.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    @Override
    public SimpleResponse saveVendor(VendorSaveRequest vendorRequest) {
        Vendor vendor = Vendor.builder()
                .firstName(vendorRequest.firstName())
                .lastName(vendorRequest.lastName())
                .image(vendorRequest.image())
                .phoneNumber(vendorRequest.phoneNumber())
                .isActive(true)
                .image("image")
                .description("Vendor")
                .build();

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Vendor with full name: %s %s successfully saved",
                        vendor.getFirstName(), vendor.getLastName()))
                .build();
    }
}
