package com.lms.Course.com.lms.Course.Service;

import com.lms.Course.Exception.CourseAlreadyExistsException;
import com.lms.Course.Exception.CourseNotFoundException;
import com.lms.Course.com.lms.Course.DAO.Course;

import java.util.List;
import java.util.Optional;

public interface CourseSevice {

    public List<Course> getAllCourse();

    public Course getCourseById(Long id) throws CourseNotFoundException;

    List<Course> getCourseBysearch(String filter);

    Course addCourse(Course course) throws CourseAlreadyExistsException;

    boolean updateCourse(Course course)throws CourseNotFoundException;

    boolean deleteCourse(Long id) throws CourseNotFoundException;
}

