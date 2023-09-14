package com.upc.minsa.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class HealthCenterDTO {
    private Long id;
    private String name;
    private String type;
    private Integer qualinfraestructure; //puede ser entre 1 y 100
    private Integer qualservice; //puede ser entre 1 y 100
    private boolean ambulance;
    private transient double qualification;
}
