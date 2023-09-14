package com.upc.minsa.service;
import com.upc.minsa.entities.HealthCenter;
import com.upc.minsa.interfaceservice.HealthCenterService;
import com.upc.minsa.repositories.HealthCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HealthCenterServiceImpl implements HealthCenterService {
    @Autowired
    private HealthCenterRepository healthCenterRepository;
    //para que un centro sea aprobado la calificacion debe ser 80 como minimo
    //Calificación = (calificación de infraestructura) * 0.35 + (calificación de servicios) * 0.65
    public HealthCenter registerHealthCenter(HealthCenter healthCenter){
        return healthCenterRepository.save(setQualificationHealthCenter(healthCenter));
    }
    public List<HealthCenter> listAllHealthCenters(){
        List<HealthCenter> l1=new ArrayList<>();
        for (HealthCenter h:healthCenterRepository.findAll()) {
            setQualificationHealthCenter(h);
            l1.add(h);
        }
        return l1;

    }
    public HealthCenter setQualificationHealthCenter(HealthCenter healthCenter){
        healthCenter.setQualification(healthCenter.
                getQualservice()*0.65 + healthCenter.getQualinfraestructure()*0.35);
        return healthCenter;
    }

    public List<HealthCenter> listAllHealthCentersByType(String type){
        List<HealthCenter> l1=new ArrayList<>();
        for (HealthCenter h1:listAllHealthCenters()) {
            if(h1.getType().equals(type))l1.add(h1);
        }
        return l1;
    }


    public String getIsHealthCenterApproved(Long id){
        HealthCenter h2 = healthCenterRepository.findById(id).get();
        setQualificationHealthCenter(h2);
        if(h2.getQualification()>=80)return "Centro de salud aprobado";
        else return "Centro de salud no aprobado";
    }



    public double getMaxQualification(){
        double proof=0;
        for (HealthCenter h1:listAllHealthCenters()) {
            if(h1.getQualification()>proof)proof=h1.getQualification();
        }
        return proof;
    }


    public List<HealthCenter> getHealthCentersOrderedByNameAsc(){
        return healthCenterRepository.findAllByOrderByNameAsc();
    }



}
