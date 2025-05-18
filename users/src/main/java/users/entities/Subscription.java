package users.entities;

import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscription")
public class Subscription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;


    // Additional fields will be added in the future as needed
    // private String status; // Status of the subscription (e.g., active, canceled)
    // private String startDate; // Start date of the subscription
    // private String endDate; // End date of the subscription
    // private String paymentMethod; // Payment method used for the subscription
    // private String planId; // ID of the subscription plan


}
