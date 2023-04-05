package com.lms.Course.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Course not found")
public class CourseNotFoundException extends Exception {
    private static Long serialVrsionUID = 1L;
}
