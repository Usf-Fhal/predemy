package courses.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;


/**
 * Represents an Enrollment entity that maps the many-to-many relationship
 * between students and courses. Uses UUID references to maintain service isolation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, referencedColumnName = "uuid")
    private Course courseId;


    // Constructor for creating new enrollment
    public Enrollment(String studentId, Course courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}