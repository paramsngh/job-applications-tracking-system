package com.ana.analytics.service;

import com.ana.analytics.dto.AnalyticsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service

public class AnalyticsService {

    private final RestTemplate restTemplate = new RestTemplate();

    public AnalyticsDto getSummary() {

        AnalyticsDto dto = new AnalyticsDto();

        Long compCount = restTemplate.getForObject(
                "http://localhost:8082/companies/count-all",
                Long.class

        );

        List<?> locations = restTemplate.getForObject(

                "http://localhost:8082/companies/group-by-location",
                List.class

        );

        List<?> industries = restTemplate.getForObject(
                "http://localhost:8082/companies/group-by-industry",
                List.class);

        // ✅ Application Service calls
        Long appCount = restTemplate.getForObject(
                "http://localhost:8080/applications/application-total",
                Long.class);

        List<?> positions = restTemplate.getForObject(
                "http://localhost:8080/applications/position-breakdown",
                List.class);

        // ✅ Set values
        dto.setCompCount(compCount);
        dto.setAppCount(appCount);
        dto.setLocationBreakdown(locations);
        dto.setIndustryBreakdown(industries);
        dto.setPositionBreakdown(positions);

        return dto;
    }

}
