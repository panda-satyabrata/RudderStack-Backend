package com.rudderstack.rudderstack.services;

import com.rudderstack.rudderstack.exceptions.DuplicateValueException;
import com.rudderstack.rudderstack.exceptions.NotFoundException;
import com.rudderstack.rudderstack.models.TrackingPlan;
import com.rudderstack.rudderstack.models.TrackingPlanDto;

import java.util.List;

public interface TrackingPlanService {

    TrackingPlanDto save(TrackingPlanDto trackingPlanDto) throws DuplicateValueException;

    TrackingPlanDto updateName(String id, String trackingPlanName) throws NotFoundException;

    TrackingPlanDto addExistingEventsToTrackingPlan(String id, String EventsName) throws NotFoundException;

    void deleteById(String id);

    TrackingPlanDto findById(String id);

    List<TrackingPlanDto> findAll();
}
