package com.jobtracker.applicationservice.repository;

import com.jobtracker.applicationservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByUserIdAndCompanyIdAndPosition(Long userId, Long companyId, String position);

    List<Application> findByUserId(Long userId);

}