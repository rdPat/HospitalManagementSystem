package com.hms;

import com.hms.entity.Appointment;
import com.hms.repository.AppointmentRepository;
import com.hms.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    AppointmentService appointmentService;

    @Test
    public void testAppointment()
    {
        Appointment appointment=Appointment.builder().appointmentTime(LocalDate.of(2025,11,11))
                .reason("fever").build();
        Appointment appointment1= appointmentService.createAppointment(appointment,1L,2L);
        System.out.println(appointment1);

       Appointment appointment2= appointmentService.reAssignAppointmentToAnotherDoctor(appointment1.getId(),3L);

        System.out.println(appointment2);


    }

}
