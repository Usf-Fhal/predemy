package courses.repositories;

import courses.entities.Enrollment;
import courses.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(path = "enrollments", collectionResourceRel = "enrollments", itemResourceRel = "enrollment")
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    
    // Find all enrollments for a specific student
    List<Enrollment> findByStudentId(String studentId);
    
    // Find all enrollments for a specific course
    List<Enrollment> findByCourseId(Course courseId);
    
    // Check if a student is enrolled in a specific course
    boolean existsByStudentIdAndCourseId(String studentId, Course courseId);
    
    // Count enrollments for a course
    long countByCourseId(Course courseId);
    
    // Count enrollments for a student
    long countByStudentId(String studentId);
}