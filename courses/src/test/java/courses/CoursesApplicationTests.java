package courses;

import courses.entities.Course;
import courses.entities.Status;
import courses.repositories.CourseRepository;
import courses.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;



@SpringBootTest
@ActiveProfiles("test")
class CoursesApplicationTests {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository courseRepository;

	private Course testCourse;

	@BeforeEach
	void setUp() {
		courseRepository.deleteAll();
		testCourse = new Course();
		testCourse.setTitle("Java Basics");
		testCourse.setPrice(new BigDecimal(100));
		testCourse.setStatus(Status.DRAFT);
		testCourse.setDescription(	"Introduction to Java programming");
	}

	@Test
	void contextLoads() {
		// context loads successfully
	}

	@Test
	void saveCourse_WithValidData_ShouldSaveSuccessfully() {
		Course savedCourse = courseService.createCourse(testCourse);
		assertNotNull(savedCourse.getUuid());
		assertEquals(testCourse.getTitle(), savedCourse.getTitle());
		assertEquals(testCourse.getDescription(), savedCourse.getDescription());
	}

	@Test
	void findCourseById_WithValidId_ShouldReturnCourse() {
		Course savedCourse = courseService.createCourse(testCourse);
		Course foundCourse = courseService.getCourseByUuid(savedCourse.getUuid());
		assertNotNull(foundCourse);
		assertEquals(savedCourse.getTitle(), foundCourse.getTitle());
	}

	@Test
	void deleteCourse_WithValidId_ShouldDeleteCourse() {
		Course savedCourse = courseService.createCourse(testCourse);
		courseService.deleteCourse(savedCourse.getUuid());
		assertFalse(courseRepository.findById(savedCourse.getUuid()).isPresent());
	}

	@Test
	void saveCourse_WithDuplicateTitle_ShouldThrowException() {
		courseService.createCourse(testCourse);
		Course duplicateCourse = new Course();
		duplicateCourse.setTitle("Java Basics");
		duplicateCourse.setDescription("Another Java course");
		assertThrows(DataIntegrityViolationException.class, () -> {
			courseService.createCourse(duplicateCourse);
		});
	}
}