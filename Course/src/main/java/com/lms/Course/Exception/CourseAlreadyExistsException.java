package com.lms.Course.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Course already exists")
public class CourseAlreadyExistsException extends Exception {
    private static Long serialVrsionUID = 1L;
}
