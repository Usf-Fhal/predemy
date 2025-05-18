package courses.services;

import courses.entities.Course;
import courses.entities.Status;
import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    Course updateCourse(String uuid, Course course);
    void deleteCourse(String uuid);
    Course getCourseByUuid(String uuid);
    List<Course> getAllCourses();
    List<Course> getCoursesByStatus(Status status);
    List<Course> getCoursesByInstructor(String instructorId);
    List<Course> searchCoursesByTitle(String title);
    List<Course> getCoursesByPriceRange(Double minPrice, Double maxPrice);
}