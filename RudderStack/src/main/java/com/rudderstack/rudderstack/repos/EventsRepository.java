package com.rudderstack.rudderstack.repos;

import com.rudderstack.rudderstack.models.Events;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventsRepository extends MongoRepository<Events, String> {
    Optional<Events> findByName(String name);
}
