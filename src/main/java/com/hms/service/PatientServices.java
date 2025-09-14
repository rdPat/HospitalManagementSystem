package com.hms.service;

import com.hms.dto.BloodGroupCountResponseEntity;
import com.hms.entity.Patient;
import com.hms.repository.PatientRepository;
import com.hms.type.BloodGroupType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServices {

    private final PatientRepository patientRepository;

   public Optional<Patient> findByName(String s)
   {
       return  patientRepository.findByPatientName(s);
   }

  public List<Patient> findByGender(String s)
  {
      return patientRepository.findByPatientGender(s);
  }

  public  List<Patient> findByBlood(BloodGroupType s)
  {
      return patientRepository.findByPatientBloodGroup(s);
  }

  public List<Patient> findByBod(LocalDate s,LocalDate e)
  {
      return  patientRepository.findByBirthDateBetween(s,e);
  }

  public int updateNameWithId(String name,Long id)
  {
      return patientRepository.updateNameWithId(name,id);
  }

  public List<BloodGroupCountResponseEntity> countByBloodGroupType()
  {
      return patientRepository.countEachBloodGroupType();
  }



}
