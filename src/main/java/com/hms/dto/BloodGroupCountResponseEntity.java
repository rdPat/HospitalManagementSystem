package com.hms.dto;

import com.hms.type.BloodGroupType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponseEntity {
    private BloodGroupType  bloodGroupType;
    private Long count;
}
