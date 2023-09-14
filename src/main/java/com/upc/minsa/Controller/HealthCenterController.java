package com.upc.minsa.Controller;

import com.upc.minsa.dto.HealthCenterDTO;
import com.upc.minsa.entities.HealthCenter;
import com.upc.minsa.interfaceservice.HealthCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HealthCenterController {
    @Autowired
    private HealthCenterService healthCenterService;
    @PostMapping("/healthcenter")
    public ResponseEntity<HealthCenterDTO> insertHealthCenter(@RequestBody HealthCenterDTO healthCenterDTO){
        HealthCenter healthCenter;
        HealthCenterDTO dto;
        try{
            healthCenter = convertToEntity(healthCenterDTO);
            healthCenter = healthCenterService.registerHealthCenter(healthCenter);
            dto = convertToDTO(healthCenter);

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido registrar");
        }
        return new ResponseEntity<HealthCenterDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/healthcenters")
    public ResponseEntity<List<HealthCenterDTO>> listAllHealthCenters(){
        List<HealthCenter> list;
        List<HealthCenterDTO> listDTO=null;
        try {
            list = healthCenterService.listAllHealthCenters();
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    @GetMapping("/healthcenters/{type}")
    public ResponseEntity<List<HealthCenterDTO>> listAllHealthCentersGivenType(@PathVariable(value = "type") String type){
        List<HealthCenter> list;
        List<HealthCenterDTO> listDTO=null;
        try {
            list = healthCenterService.listAllHealthCentersByType(type);
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    @GetMapping("/healthcenter/{id}")
    public String getIsHealthCenterAproved(@PathVariable(value = "id") Long id)
    {
        String mensaje;
        try {
            mensaje = healthCenterService.getIsHealthCenterApproved(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el centro de salud");
        }
        return mensaje;
    }

    @GetMapping("/healthcenters/maxqualification")
    public double getMaxQualification(){
        double var;
        try {
            var= healthCenterService.getMaxQualification();
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el maximo de calificacion");
        }
        return var;
    }

    
    @GetMapping("/healthcenters/orderedbynameasc")
    public ResponseEntity<List<HealthCenterDTO>> listAllHealthCentersorderedByNameAsc(){
        List<HealthCenter> list;
        List<HealthCenterDTO> listDTO=null;
        try {
            list = healthCenterService.getHealthCentersOrderedByNameAsc();
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    private HealthCenter convertToEntity(HealthCenterDTO healthCenterDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(healthCenterDTO, HealthCenter.class);
    }

    private HealthCenterDTO convertToDTO(HealthCenter healthCenter){
        ModelMapper modelMapper =new ModelMapper();
        return modelMapper.map(healthCenter, HealthCenterDTO.class);
    }

    private List<HealthCenterDTO> convertToListDTO(List<HealthCenter> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
