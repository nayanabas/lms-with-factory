package com.lms.Course.com.lms.Course.Repo;

import com.lms.Course.com.lms.Course.DAO.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query("Select c from Course c where name like %?1% or technology like %?1% ")
    List<Course> findByname(String filter);

    Optional<Course> findByCourseId(Long id);
}
