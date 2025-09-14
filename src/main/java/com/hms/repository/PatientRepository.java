package com.hms.repository;

import com.hms.dto.BloodGroupCountResponseEntity;
import com.hms.entity.Patient;
import com.hms.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findByPatientName(String name);
    List<Patient> findByPatientGender(String name);
    List<Patient> findByPatientBloodGroup(BloodGroupType grp);

    @Query("select p from Patient p where p.patientDOB>=?1 AND p.patientDOB<=?2")
    List<Patient> findByBirthDateBetween(@Param("start") LocalDate startDate,@Param("end") LocalDate endDate);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.patientName=:name where p.patientId=:id")
    int updateNameWithId(@Param("name") String name,@Param("id") Long id);



    @Query("select new com.hms.dto.BloodGroupCountResponseEntity(p.patientBloodGroup,count(p)) from Patient p group by" +
            " p.patientBloodGroup ")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    @Query(value = "select * from patient p", nativeQuery = true)
    Page<Patient> getAllPatient(PageRequest pageable);

    @Query("select p from Patient p left join fetch p.appointments")
   List<Patient> findAllPatientWithAppointment();

}
