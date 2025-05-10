package users.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(255)")
    private String uuid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "uuid")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private double averageRating;

    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private boolean isVerified;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Custom constructor with required fields
    public Instructor(User user, String specialization, String qualification) {
        this.user = user;
        this.specialization = specialization;
        this.qualification = qualification;
        this.averageRating = 0.0;
        this.isVerified = false;
    }
}