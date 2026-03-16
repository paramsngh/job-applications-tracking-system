package com.company.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "company")

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comp_seq")
    @SequenceGenerator(name = "comp_seq", sequenceName = "comp_seq", allocationSize = 1)
    private Long compId;

    @NotBlank(message = "Company name cannot be empty")
    private String compName;
    private String industry;
    private String location;
    private String website;

    public Company() {
    };

    public Long getCompId() {
        return compId;
    }

    public String getCompName() {
        return compName;
    }

    public String getLocation() {
        return location;
    }

    public String getIndustry() {
        return industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setCompId(Long compId) {
        this.compId = compId;

    }

    public void setCompName(String compName) {
        this.compName = compName;

    }

    public void setLocation(String location) {
        this.location = location;

    }

    public void setIndustry(String industry) {
        this.industry = industry;

    }

    public void setWebsite(String website) {
        this.website = website;

    }

}