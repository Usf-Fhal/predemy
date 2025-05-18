package courses.repositories;

import courses.entities.Course;
import courses.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(path = "courses", collectionResourceRel = "courses", itemResourceRel = "course")
public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByStatus(Status status);
    List<Course> findByInstructorId(String instructorId);
    List<Course> findByTitleContainingIgnoreCase(String title);
    List<Course> findByPriceBetween(Double minPrix, Double maxPrix);

}