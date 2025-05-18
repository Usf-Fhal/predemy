package users.repositories;

import users.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Repository interface for managing {@link Subscription} entities.
 * <p>
 * This repository is currently only needed for the {@code save} method.
 * </p>
 */
@RepositoryRestResource(path = "subscriptions", collectionResourceRel = "subscriptions", itemResourceRel = "subscription")
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    
    
}