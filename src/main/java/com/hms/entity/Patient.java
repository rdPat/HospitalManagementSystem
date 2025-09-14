package com.hms.entity;

import com.hms.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
//to string is used to get data from object as i wanted to get data on console
@ToString
@Getter
@Setter
//@Table is used for changing table configurations such as name of table
@Table(
       // name="patient_tbl",
        uniqueConstraints = {
                //@UniqueConstraint(name = "unique_email_constraint",columnNames = {"PatientEmail"}),
                @UniqueConstraint(name="unique_name_bod_constraint",columnNames = {"PatientName","PatientDOB"})
                },
                indexes={@Index(name = "idx_patient_bod",columnList = "PatientDOB")}
)
/*
regarding table name: if you provide table in in CamelCase format it will
be converted to snake case in db eg. PatientTable --->> patient_table
* */
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String patientName;

   // @Column(name="patient_dob")
    private LocalDate patientDOB;

    @Column(unique = true,nullable=false,length = 40)
    private String patientEmail;

    private String patientGender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate CreatedOn;

    @Enumerated(EnumType.STRING)
    private BloodGroupType patientBloodGroup;

    /* below if you dont provide joincolumn name then jpa
    * automatically generate name of column. eg. here Insurance entity has id as primary key so
    * in patient table new column is created with name --->insurance_id   insurance is instance name here
    * */
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")    //Owning side  also it can be null as not every patient has insurance
    private Insurance insurance;

    //bz of cascade.remove when patient is deleted all appointment get deleted
    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},orphanRemoval = true)
    private List<Appointment> appointments;

}
