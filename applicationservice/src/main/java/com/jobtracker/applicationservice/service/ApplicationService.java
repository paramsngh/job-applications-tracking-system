package com.jobtracker.applicationservice.service;

import com.jobtracker.applicationservice.entity.Application;
import com.jobtracker.applicationservice.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import com.jobtracker.applicationservice.client.CompanyClient;
import com.jobtracker.applicationservice.dto.CompanyDTO;

import java.util.ArrayList;
import java.util.List;
import com.jobtracker.applicationservice.dto.ApplicationDTO;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CompanyClient companyClient;

    public ApplicationService(ApplicationRepository applicationRepository,
            CompanyClient companyClient) {
        this.applicationRepository = applicationRepository;
        this.companyClient = companyClient;
    }

    public Application createApplication(Application application) {

        boolean exists = applicationRepository.existsByUserIdAndCompanyIdAndPosition(
                application.getUserId(),
                application.getCompanyId(),
                application.getPosition());

        if (exists) {
            throw new RuntimeException("Application already exists");
        }

        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {

        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new RuntimeException("Application does not exist");

        }
        applicationRepository.deleteById(id);
    }

    public List<Application> getApplicationsByUserId(Long userId) {
        return applicationRepository.findByUserId(userId);
    }

    public CompanyDTO getCompanyForApplication(Long applicationId) {

        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return companyClient.getCompanyById(app.getCompanyId());
    }

    public Long getCountAllApplications() {
        return applicationRepository.count();
    }

    public List<ApplicationDTO> getPositionBreakdown() {
        List<Object[]> data = applicationRepository.applicationByPosition();
        List<ApplicationDTO> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            Object[] row = data.get(i);
            String label = (String) row[0];
            Long count = (Long) row[1];

            result.add(new ApplicationDTO(label, count));

        }

        return result;

    }

}