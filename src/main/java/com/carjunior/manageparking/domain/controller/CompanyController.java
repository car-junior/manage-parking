package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.company.CompanyCreate;
import com.carjunior.manageparking.domain.entity.Company;
import com.carjunior.manageparking.domain.service.CompanyService;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    public ResponseEntity<Object> createCompany(@Valid @RequestBody CompanyCreate companyCreateRecord) {
        var company = companyService
                .saveCompany(modelMapperService.toObject(Company.class, companyCreateRecord));
        return ResponseEntity.ok(company);
    }
}
