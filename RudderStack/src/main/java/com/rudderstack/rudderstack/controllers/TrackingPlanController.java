package com.rudderstack.rudderstack.controllers;

import com.rudderstack.rudderstack.exceptions.DuplicateValueException;
import com.rudderstack.rudderstack.exceptions.NotFoundException;
import com.rudderstack.rudderstack.models.TrackingPlanDto;
import com.rudderstack.rudderstack.services.TrackingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trackingplan")
public class TrackingPlanController {

    @Autowired
    TrackingPlanService trackingPlanService;

    @GetMapping(value = "/{trackingPlanId}", produces = "application/json")
    public ResponseEntity<TrackingPlanDto> getTrackingPlanById(@PathVariable String trackingPlanId){
        TrackingPlanDto trackingPlanDto = trackingPlanService.findById(trackingPlanId);
        if(trackingPlanDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trackingPlanDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<TrackingPlanDto>> getTrackingPlans(){
        List<TrackingPlanDto> trackingPlanDtoList = trackingPlanService.findAll();
        return new ResponseEntity<>(trackingPlanDtoList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping(produces = "application/json")
    public ResponseEntity<TrackingPlanDto> saveTrackingPlan(@RequestBody TrackingPlanDto trackingPlanDto) throws DuplicateValueException {
        TrackingPlanDto savedTrackingPlanDto = trackingPlanService.save(trackingPlanDto);
        return new ResponseEntity<>(savedTrackingPlanDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{trackingPlanId}/name", produces = "application/json")
    public ResponseEntity<TrackingPlanDto> updateTrackingPlanName(@PathVariable String trackingPlanId, @RequestParam String trackingPlanName) throws NotFoundException {
        TrackingPlanDto updatedTrackingPlanDto = trackingPlanService.updateName(trackingPlanId, trackingPlanName);
        return new ResponseEntity<>(updatedTrackingPlanDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{trackingPlanId}/events", produces = "application/json")
    public ResponseEntity<TrackingPlanDto> addExistingEventsToTrackingPlan(@PathVariable String trackingPlanId, @RequestParam String eventsName) throws NotFoundException {
        TrackingPlanDto updatedTrackingPlanDto = trackingPlanService.addExistingEventsToTrackingPlan(trackingPlanId, eventsName);
        return new ResponseEntity<>(updatedTrackingPlanDto, HttpStatus.OK);
    }

}
