package com.jobtracker.applicationservice.client;

import com.jobtracker.applicationservice.dto.CompanyDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CompanyClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public CompanyDTO getCompanyById(Long compId) {
        String url = "http://localhost:8082/companies/" + compId;
        return restTemplate.getForObject(url, CompanyDTO.class);
    }
}