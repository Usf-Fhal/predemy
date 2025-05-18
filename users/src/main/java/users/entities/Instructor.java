package users.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instructors")
public class Instructor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid", nullable = false)
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


    // Custom constructor with required fields
    public Instructor(User user, String specialization, String qualification) {
        this.user = user;
        this.specialization = specialization;
        this.qualification = qualification;
        this.averageRating = 0.0;
        this.isVerified = false;
    }
}