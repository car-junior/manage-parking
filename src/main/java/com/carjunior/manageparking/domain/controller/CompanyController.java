package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.company.CompanyCreateDto;
import com.carjunior.manageparking.domain.dto.company.CompanyDetailDto;
import com.carjunior.manageparking.domain.dto.company.CompanyListDto;
import com.carjunior.manageparking.domain.entity.Company;
import com.carjunior.manageparking.domain.service.CompanyService;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    public ResponseEntity<CompanyDetailDto> create(@Valid @RequestBody CompanyCreateDto companyCreateDto) {
        var company = companyService.saveCompany(modelMapperService.toObject(Company.class, companyCreateDto));
        return ResponseEntity.ok(modelMapperService.toObject(CompanyDetailDto.class, company));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDetailDto> getById(@PathVariable(name = "companyId") Long companyId) {
        return ResponseEntity.ok(
                modelMapperService.toObject(CompanyDetailDto.class, companyService.getCompanyById(companyId))
        );
    }

    @GetMapping
    public ResponseEntity<List<CompanyListDto>> getAll() {
        return ResponseEntity.ok(modelMapperService.toList(CompanyListDto.class, companyService.getAllCompany()));
    }


}
