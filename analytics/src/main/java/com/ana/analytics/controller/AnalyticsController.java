package com.ana.analytics.controller;

import com.ana.analytics.dto.AnalyticsDto;
import com.ana.analytics.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public AnalyticsDto getSummary() {
        return analyticsService.getSummary();
    }
}