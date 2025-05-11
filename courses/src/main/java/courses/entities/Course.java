package courses.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Represents a Course entity in the system.
 * Each service should be standalone, and the shared ID reference approach is used.
 * To retrieve the `instructor_id` field, a REST call will be sent to the Users service.
 * This entity is mapped to the "courses" table in the database and contains
 * information about a course, such as its title, description, price, and
 * publication status. It also tracks the instructor associated with the course
 * and timestamps for creation and updates.
 * 
 * Note: Additional fields such as thumbnail URL, preview video URL, duration,
 * total lectures, rating, enrollment count, category, and sections will be
 * added in the future as the system evolves.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(255)")
    private String uuid;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // The `instructor_id` field is a string that references the UUID of the instructor
    private String instructor_id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    // Additional fields to be added later
    // @Column(name = "thumbnail_url")
    // private String thumbnailUrl;

    // @Column(name = "preview_video_url")
    // private String previewVideoUrl;

    // private double duration;

    // @Column(name = "total_lectures")
    // private int totalLectures;

    // private double rating;

    // @Column(name = "enrollment_count")
    // private int enrollmentCount;

    // Will be implemented later when other entities are created
    // @ManyToOne
    // private String category_id;

    // @OneToMany(mappedBy = "course")
    // private List<String> sections;
}
