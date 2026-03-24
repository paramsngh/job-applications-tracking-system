package com.ana.analytics.dto;

import java.util.List;

public class AnalyticsDto {

    private Long compCount;
    private Long appCount;

    private List<?> locationBreakdown;
    private List<?> industryBreakdown;
    private List<?> positionBreakdown;

    public AnalyticsDto() {
    }

    public Long getCompCount() {
        return compCount;
    }

    public void setCompCount(Long compCount) {
        this.compCount = compCount;
    }

    public Long getAppCount() {
        return appCount;
    }

    public void setAppCount(Long appCount) {
        this.appCount = appCount;
    }

    public List<?> getLocationBreakdown() {
        return locationBreakdown;
    }

    public void setLocationBreakdown(List<?> locationBreakdown) {
        this.locationBreakdown = locationBreakdown;
    }

    public List<?> getIndustryBreakdown() {
        return industryBreakdown;
    }

    public void setIndustryBreakdown(List<?> industryBreakdown) {
        this.industryBreakdown = industryBreakdown;
    }

    public List<?> getPositionBreakdown() {
        return positionBreakdown;
    }

    public void setPositionBreakdown(List<?> positionBreakdown) {
        this.positionBreakdown = positionBreakdown;
    }
}