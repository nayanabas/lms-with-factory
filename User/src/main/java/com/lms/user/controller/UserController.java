package com.lms.user.controller;

import com.lms.user.dto.Course;
import com.lms.user.dto.CourseList;
import com.lms.user.entity.User;
import com.lms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/userlookup")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping(path= "/getAllCourse")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getAllCourse(){

        CourseList courseList = null;
        ResponseEntity<CourseList> responseEntity;

        try{
            responseEntity = restTemplate
                    .getForEntity("http://localhost:8089/api/v1/CourseLookup/" ,
                            CourseList.class);




            System.out.println(responseEntity.getStatusCode().value());
        }catch(Exception e){
            System.out.println(e.getMessage());
            return  new ResponseEntity<String>("Course details not found", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<CourseList>(responseEntity.getBody(), HttpStatus.OK);

    }
    @GetMapping(path = "/getAllCoursebyId/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getAllCoursebyId(@PathVariable Long id){


        ResponseEntity<Course> responseEntity;

        try{
          responseEntity = restTemplate
                    .getForEntity("http://localhost:8089/api/v1/CourseLookup/byid/" +id,
                            Course.class);



        }catch(Exception e){
            return new ResponseEntity<String>("No Course Found", HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<Course>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping(path = "/getAllCoursebyFilter/{filter}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getCoursesBySearch(@PathVariable String filter){


        ResponseEntity<CourseList> responseEntity= null;

        try{
            if(!filter.isEmpty()){
                responseEntity = restTemplate
                        .getForEntity("http://localhost:8089/api/v1/CourseLookup/filter/" +filter,
                                CourseList.class);

            }



        }catch(Exception e){
            return new ResponseEntity<String>("No Course Found", HttpStatus.NOT_FOUND);
        }


        return  new ResponseEntity<CourseList>(responseEntity.getBody(), HttpStatus.OK);
    }



    @PostMapping(path = "/addCourse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addCourse(@RequestBody Course course){

        ResponseEntity<Course> responseEntity= null;

        try{

                responseEntity = restTemplate
                        .postForEntity("http://localhost:8089/api/v1/CourseLookup/",course,
                                Course.class);

        }catch(Exception e){
            return new ResponseEntity<String>("course not added", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return  new ResponseEntity<Course>(responseEntity.getBody(), HttpStatus.OK);
    }
    @PutMapping(path = "/updateCourse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateCourse(@RequestBody Course course){

        ResponseEntity<String> responseEntity= null;

        try{

            HttpEntity<Course> entity = new HttpEntity(course);
            responseEntity = restTemplate.exchange("http://localhost:8089/api/v1/CourseLookup/update", HttpMethod.PUT,entity,String.class);


        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("No Course updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return  new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){

        ResponseEntity<String> responseEntity= null;

        try{

            HttpEntity<Course> entity = new HttpEntity(id);
            responseEntity = restTemplate.exchange("http://localhost:8089/api/v1/CourseLookup/delete/"+id, HttpMethod.DELETE,entity,String.class);


        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("No Course deleted", HttpStatus.NOT_FOUND);
        }


        return  new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
    }
}
