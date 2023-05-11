package com.manas.service;

import com.manas.dto.request.VendorSaveRequest;
import com.manas.dto.response.SimpleResponse;

public interface VendorService {
    //save, findById, getAll, getWithSearch,
    // update, delete, activateDeActivateVendor, exportVendorExcel

    SimpleResponse save(VendorSaveRequest vendorRequest);
}