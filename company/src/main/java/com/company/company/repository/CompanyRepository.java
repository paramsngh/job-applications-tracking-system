package com.company.company.repository;

import com.company.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByCompName(String compName);

    Company findByCompName(String compName);

    List<Company> findByLocation(String location);

    List<Company> findByIndustry(String industry);

    @Query("SELECT c.industry, COUNT(c) FROM Company c GROUP BY c.industry")
    List<Object[]> countByIndustry();

    @Query("SELECT c.location, COUNT(c) FROM Company c GROUP BY c.location")
    List<Object[]> countByLocation();

}
