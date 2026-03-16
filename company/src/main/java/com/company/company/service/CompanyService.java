package com.company.company.service;

import com.company.company.entity.Company;
import com.company.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public List<Company> getCompaniesByLocation(String location) {
        return companyRepository.findByLocation(location);
    }

    public List<Company> getCompaniesByIndustry(String industry) {
        return companyRepository.findByIndustry(industry);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

}
