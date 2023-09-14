package com.upc.minsa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "healthcenter")
public class HealthCenter {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "healthcenter", unique = true, nullable = false)
    private String name;
    @Column(name = "type", nullable = false, length = 1) //puede ser H o C
    private String type;
    @Column(name = "qualinfraestructure", nullable = false)
    private Integer qualinfraestructure; //puede ser entre 1 y 100
    @Column(name = "qualservice", nullable = false)
    private Integer qualservice; //puede ser entre 1 y 100
    @Column(name = "ambulance", nullable = false)
    private boolean ambulance;
    private transient double qualification;
}
