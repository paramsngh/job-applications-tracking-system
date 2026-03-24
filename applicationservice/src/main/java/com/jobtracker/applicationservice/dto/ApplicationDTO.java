package com.jobtracker.applicationservice.dto;

public class ApplicationDTO {

    private String label;
    private Long count;

    public ApplicationDTO() {
    }

    public Long getCount() {
        return count;

    }

    public String getLabel() {
        return label;
    }

    public ApplicationDTO(String label, Long count) {
        this.label = label;
        this.count = count;
    }

}
