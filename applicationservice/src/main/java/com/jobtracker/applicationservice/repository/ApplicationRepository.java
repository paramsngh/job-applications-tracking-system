package com.jobtracker.applicationservice.repository;

import com.jobtracker.applicationservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByUserIdAndCompanyIdAndPosition(Long userId, Long companyId, String position);

    List<Application> findByUserId(Long userId);

    @Query("SELECT a.position, COUNT(a) FROM Application a GROUP BY a.position")
    List<Object[]> applicationByPosition();

}