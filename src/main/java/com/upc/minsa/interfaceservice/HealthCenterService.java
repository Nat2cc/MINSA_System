package com.upc.minsa.interfaceservice;

import com.upc.minsa.entities.HealthCenter;

import java.util.List;

public interface HealthCenterService {
    public HealthCenter registerHealthCenter(HealthCenter healthCenter);
    public List<HealthCenter> listAllHealthCenters();
    public HealthCenter setQualificationHealthCenter(HealthCenter healthCenter);
    public List<HealthCenter> listAllHealthCentersByType(String type);
    public String getIsHealthCenterApproved(Long id);
    public double getMaxQualification();
   public List<HealthCenter> getHealthCentersOrderedByNameAsc();
}
