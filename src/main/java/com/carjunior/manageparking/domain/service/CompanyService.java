package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.entity.Company;
import com.carjunior.manageparking.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).get();
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }
}
