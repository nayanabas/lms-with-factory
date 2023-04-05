package com.lms.Course.com.lms.Course.Service;

import com.lms.Course.Exception.CourseAlreadyExistsException;
import com.lms.Course.Exception.CourseNotFoundException;
import com.lms.Course.com.lms.Course.DAO.Course;
import com.lms.Course.com.lms.Course.Repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseSevice {

    @Autowired
    private CourseRepo courseRepo;

    @Override

    public List<Course> getAllCourse() {
        List<Course> courseList =courseRepo.findAll();
        if(courseList!=null && !courseList.isEmpty()){
            return courseList;
        }
       return null;
    }

    @Override
    public Course getCourseById(Long id) throws CourseNotFoundException {
       return  courseRepo.findByCourseId(id).orElseThrow(CourseNotFoundException::new);

    }

    @Override
    public List<Course> getCourseBysearch(String filter) {
        List<Course> course = courseRepo.findByname(filter);
        return course;
    }

    @Override
    public Course addCourse(Course course) throws CourseAlreadyExistsException {
        Optional<Course> course1 = courseRepo.findByCourseId(course.getCourseId());
        if(course1.isPresent()){
            throw new CourseAlreadyExistsException();
        }

        return courseRepo.save(course);
    }

    @Override
    public boolean updateCourse(Course course)throws CourseNotFoundException {
        courseRepo.findByCourseId(course.getCourseId()).orElseThrow(CourseNotFoundException::new);
            courseRepo.save(course);
            return true;

    }

    @Override
    public boolean deleteCourse(Long id) throws CourseNotFoundException {
        Optional<Course> course1 =  courseRepo.findById(id);
        if(!course1.isPresent()){
            throw new CourseNotFoundException();

        }
        courseRepo.deleteById(id);
        return true;
    }





}
