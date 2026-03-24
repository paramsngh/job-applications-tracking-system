package com.company.company.controller;

import com.company.company.entity.Company;
import com.company.company.service.CompanyService;
import org.springframework.web.bind.annotation.*;
import com.company.company.dto.CompanyDTO;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") Long id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/location/{location}")
    public List<Company> getCompaniesByLocation(@PathVariable("location") String location) {
        return companyService.getCompaniesByLocation(location);
    }

    @GetMapping("/industry/{industry}")
    public List<Company> getCompaniesByIndustry(@PathVariable("industry") String industry) {
        return companyService.getCompaniesByIndustry(industry);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
    }

    @GetMapping("/count-all")
    public Long getCompanyCount() {

        return companyService.getCompanyCount();

    }

    @GetMapping("/group-by-location")
    public List<CompanyDTO> getLocationBreakdown() {
        return companyService.getLocationBreakdown();
    }

    @GetMapping("/group-by-industry")
    public List<CompanyDTO> getIndustryBreakdown() {
        return companyService.getIndustryBreakdown();
    }

}