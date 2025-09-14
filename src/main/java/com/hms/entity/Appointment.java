package com.hms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate appointmentTime;

    @Column(nullable = false, length = 500)
    private  String reason;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)   // nullable false bz patient should be there for apointment
    private Patient patient;


    @ManyToOne()
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
