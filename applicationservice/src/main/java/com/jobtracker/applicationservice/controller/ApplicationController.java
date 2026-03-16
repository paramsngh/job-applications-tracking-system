package com.jobtracker.applicationservice.controller;

import com.jobtracker.applicationservice.entity.Application;
import com.jobtracker.applicationservice.service.ApplicationService;
import org.springframework.web.bind.annotation.*;
import com.jobtracker.applicationservice.dto.CompanyDTO;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/{id}/company")
    public CompanyDTO getCompanyForApplication(@PathVariable("id") Long id) {
        return applicationService.getCompanyForApplication(id);
    }

    // Create application
    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return applicationService.createApplication(application);
    }

    // Get all applications
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    // Get application by ID
    @GetMapping("/{id}")
    public Application getApplication(@PathVariable("id") Long id) {
        return applicationService.getApplicationById(id);
    }

    // Delete application
    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable("id") Long id) {
        applicationService.deleteApplication(id);
    }

    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUserId(@PathVariable("userId") Long userId) {
        return applicationService.getApplicationsByUserId(userId);
    }
}