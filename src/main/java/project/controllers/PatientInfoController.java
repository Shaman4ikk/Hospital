package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dto.PatientInfoDTO;
import project.servicesentity.PatientInfoService;

import java.util.*;

@RestController
@RequestMapping("/patientsInfo")
public class PatientInfoController {

    private final PatientInfoService patientInfoService;

    @Autowired
    public  PatientInfoController(PatientInfoService patientInfoService){
        this.patientInfoService = patientInfoService;
    }

    @GetMapping
    public ResponseEntity<List<PatientInfoDTO>> getPatientsInfo(){
        return new ResponseEntity<>(patientInfoService.getPatientsInfo(), HttpStatus.OK);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<PatientInfoDTO> getPatientInfoById(@PathVariable Long id){
        return new ResponseEntity<>(patientInfoService.getPatientInfoById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientInfoDTO> create(@RequestBody PatientInfoDTO patientInfo){
        patientInfoService.create(patientInfo);
        return new ResponseEntity<>(patientInfo, HttpStatus.OK);
    }

    @PutMapping
    public void update(@RequestBody PatientInfoDTO patientInfo){
        patientInfoService.update(patientInfo);
    }

    @DeleteMapping
    public void delete(@RequestBody PatientInfoDTO patientInfo){
        patientInfoService.delete(patientInfo);
    }
}
