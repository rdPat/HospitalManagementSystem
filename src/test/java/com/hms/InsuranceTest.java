package com.hms;

import com.hms.entity.Insurance;
import com.hms.entity.Patient;
import com.hms.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsurance()
    {
        Insurance insurance=Insurance.builder()
                .policyNumber("HDFC_123")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,12)).build();
        Patient patient=insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

        insuranceService.removeInsuranceFromPatient(patient.getPatientId());

    }

}
