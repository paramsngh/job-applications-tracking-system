package com.company.company.repository;

import com.company.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByCompName(String compName);

    Company findByCompName(String compName);

    List<Company> findByLocation(String location);

    List<Company> findByIndustry(String industry);

}
