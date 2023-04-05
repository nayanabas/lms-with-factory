package com.lms.Course.com.lms.Course.Controller;


import com.lms.Course.Exception.CourseAlreadyExistsException;
import com.lms.Course.Exception.CourseNotFoundException;
import com.lms.Course.com.lms.Course.DAO.Course;
import com.lms.Course.com.lms.Course.DAO.CourseList;
import com.lms.Course.com.lms.Course.Service.CourseServiceImpl;
import com.lms.Course.com.lms.Course.configuration.JwtTokenUtil;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/CourseLookup")
public class CourseController {

    @Autowired
    private CourseServiceImpl coruseImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path = "/")
    public ResponseEntity<?> addCourse(@RequestBody Course course) throws CourseAlreadyExistsException {



        Course course1 = coruseImpl.addCourse(course);
        if(course1!=null){
            return new ResponseEntity<Course>(course1, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Course not added", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(path = "/update")
    public ResponseEntity<?> updateCourse(@RequestBody Course course) throws CourseNotFoundException{

       if(coruseImpl.updateCourse(course)){
           return new ResponseEntity<String>("Course updated successfully", HttpStatus.OK);
       }


        return new ResponseEntity<String>("Course not updated", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> getAllCourse(){

        List<Course> courses = coruseImpl.getAllCourse();

        CourseList courseList = new CourseList();
        courseList.setCourseList(courses);
        System.out.println(courses.toString());
        if(courses!=null){
            return new ResponseEntity<CourseList>(courseList, HttpStatus.OK);
        }
        return  new ResponseEntity<String>("Course details not found", HttpStatus.NO_CONTENT);

    }
    @GetMapping(path = "/byid/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) throws CourseNotFoundException{

        System.out.println(coruseImpl.getCourseById(id));

            return new ResponseEntity<Course>(coruseImpl.getCourseById(id),HttpStatus.OK);





    }
    @GetMapping(path = "/filter/{filter}")
    public ResponseEntity<?> getCourseBysearch(@PathVariable String filter){
        List<Course> response = coruseImpl.getCourseBysearch(filter);

        CourseList courseList = new CourseList();
        courseList.setCourseList(response);

        if (response != null && !response.isEmpty()) {
            return new ResponseEntity<CourseList>(courseList,HttpStatus.OK);
        }


        return new ResponseEntity<String>("No course found", HttpStatus.NOT_FOUND);


    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) throws CourseNotFoundException {
        coruseImpl.deleteCourse(id);


        return new ResponseEntity<String>("Course deleted successfully",HttpStatus.OK);


    }
}
