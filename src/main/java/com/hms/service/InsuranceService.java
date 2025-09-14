package com.hms.service;

import com.hms.entity.Insurance;
import com.hms.entity.Patient;
import com.hms.repository.InsuranceRepository;
import com.hms.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientId)
    {
        Patient patient=patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient Not found with given id"+patientId));

        //setting for maintaining data consistency in db
        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return patient;
    }

    @Transactional
    public Patient removeInsuranceFromPatient(Long patientId)
    {
        Patient patient =patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(null);
        return patient;
    }

}
