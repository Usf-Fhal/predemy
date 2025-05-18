package courses.services;

import courses.entities.Course;
import courses.entities.Status;
import courses.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        // Set initial status to DRAFT
        course.setStatus(Status.DRAFT);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(String uuid, Course course) {
        Course existingCourse = getCourseByUuid(uuid);
        
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setPrice(course.getPrice());
        existingCourse.setStatus(course.getStatus());
        existingCourse.setInstructorId(course.getInstructorId());
        
        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(String uuid) {
        Course course = getCourseByUuid(uuid);
        courseRepository.delete(course);
    }

    @Override
    public Course getCourseByUuid(String uuid) {
        return courseRepository.findById(uuid)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with uuid: " + uuid));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByStatus(Status status) {
        return courseRepository.findByStatus(status);
    }

    @Override
    public List<Course> getCoursesByInstructor(String instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    @Override
    public List<Course> searchCoursesByTitle(String title) {
        return courseRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Course> getCoursesByPriceRange(Double minPrice, Double maxPrice) {
        return courseRepository.findByPriceBetween(minPrice, maxPrice);
    }
}