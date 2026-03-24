package com.company.company.dto;

public class CompanyDTO {

    private String label;
    private Long count;

    public CompanyDTO(String label, Long count) {
        this.label = label;
        this.count = count;
    }

    public String getLabel() {
        return label;
    }

    public Long getCount() {
        return count;
    }

}
