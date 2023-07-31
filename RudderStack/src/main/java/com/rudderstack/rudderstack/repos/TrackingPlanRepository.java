package com.rudderstack.rudderstack.repos;

import com.rudderstack.rudderstack.models.TrackingPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackingPlanRepository extends MongoRepository<TrackingPlan, String> {
}
