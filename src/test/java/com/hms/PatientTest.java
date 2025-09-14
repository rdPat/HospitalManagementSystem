package com.hms;

import com.hms.dto.BloodGroupCountResponseEntity;
import com.hms.entity.Patient;
import com.hms.repository.PatientRepository;
import com.hms.type.BloodGroupType;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.sound.midi.Soundbank;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

   @Test
    public void testFindByName()
   {
       System.out.println("find by patient name -------------------");
       Optional<Patient> p=patientRepository.findByPatientName("Jane Smith");
       System.out.println(p.toString());

       System.out.println("find by patient Gender -------------------");
       List<Patient> p1=patientRepository.findByPatientGender("M");
       for (Patient i : p1) {
           System.out.println(i);
       }

       System.out.println("find by patient blood group -------------------");
       List<Patient> p2=patientRepository.findByPatientBloodGroup(BloodGroupType.A_POSITIVE);
       for (Patient i : p2) {
           System.out.println(i);
       }

       System.out.println("find by patient DOB -------------------");
       LocalDate start= LocalDate.parse("1985-06-15");
       LocalDate end= LocalDate.parse("1990-11-22");
       List<Patient> p3=patientRepository.findByBirthDateBetween(start,end);
       for (Patient i : p3) {
           System.out.println(i);
       }
   }

   @Test
    public void testUpdateApi()
   {
       int stat=0;
       stat=patientRepository.updateNameWithId("Kelly Divine",1L);
       System.out.println("Updated :"+stat);
   }

   //DTO projection test
    @Test
    public void testBloodGroupCount()
    {
        List<BloodGroupCountResponseEntity> p=patientRepository.countEachBloodGroupType();
        for(BloodGroupCountResponseEntity x: p)
        {
            System.out.println(x.toString());
        }

    }

    @Test
    public void getAllPatientDtls()
    {
        Page<Patient> p=patientRepository.getAllPatient(PageRequest.of(1,2, Sort.by("patient_name")));
        for(Patient x: p)
        {
            System.out.println(x.toString());
        }
    }

    @Test
    public void getAllPatientWithAppointments()
    {
        List<Patient> patient=patientRepository.findAllPatientWithAppointment();
        System.out.println(patient);
    }

}
