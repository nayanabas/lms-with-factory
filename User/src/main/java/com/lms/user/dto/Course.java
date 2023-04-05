package com.lms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {


    private Long courseId;


    private String name;


    private String technology;


    private int duration;

    private String description;

}