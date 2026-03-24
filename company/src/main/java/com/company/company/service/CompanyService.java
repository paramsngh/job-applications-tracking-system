package com.company.company.service;

import com.company.company.dto.CompanyDTO;
import com.company.company.entity.Company;
import com.company.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(Company company) {

        if (companyRepository.findByCompName(company.getCompName()) != null) {
            throw new RuntimeException("Company already exists");

        }
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
        List<Company> companies = companyRepository.findByLocation(location);

        if (companies.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    
        return companies;
    }

    public List<Company> getCompaniesByIndustry(String industry) {
        List<Company> companies = companyRepository.findByIndustry(industry);

        if (companies.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found");
        }
    
        return companies;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void deleteCompany(Long id) {

        if (!companyRepository.findById(id).isPresent()) {
            throw new RuntimeException("Company not found");
        }
        companyRepository.deleteById(id);
    }

    public Long getCompanyCount() {
        return companyRepository.count();
    }

    public List<CompanyDTO> getIndustryBreakdown() {

        List<Object[]> data = companyRepository.countByIndustry();

        List<CompanyDTO> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            Object[] row = data.get(i);
            String label = (String) row[0];
            Long count = (Long) row[1];

            result.add(new CompanyDTO(label, count));

        }

        return result;

    }

    public List<CompanyDTO> getLocationBreakdown() {

        List<Object[]> data = companyRepository.countByLocation();

        List<CompanyDTO> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            Object[] row = data.get(i);
            String label = (String) row[0];
            Long count = (Long) row[1];

            result.add(new CompanyDTO(label, count));

        }

        return result;

    }

}
