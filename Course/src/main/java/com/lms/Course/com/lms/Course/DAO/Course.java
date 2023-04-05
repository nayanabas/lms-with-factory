package com.lms.Course.com.lms.Course.DAO;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;


    private String name;


    private String technology;


    private int duration;

    private String description;

}
