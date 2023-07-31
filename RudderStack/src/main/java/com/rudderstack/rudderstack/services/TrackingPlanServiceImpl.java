package com.rudderstack.rudderstack.services;

import com.rudderstack.rudderstack.exceptions.DuplicateValueException;
import com.rudderstack.rudderstack.exceptions.NotFoundException;
import com.rudderstack.rudderstack.models.Events;
import com.rudderstack.rudderstack.models.EventsDto;
import com.rudderstack.rudderstack.models.TrackingPlan;
import com.rudderstack.rudderstack.models.TrackingPlanDto;
import com.rudderstack.rudderstack.repos.TrackingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrackingPlanServiceImpl implements TrackingPlanService{

    @Autowired
    TrackingPlanRepository trackingPlanRepository;

    @Autowired
    EventsService eventsService;

    @Override
    public TrackingPlanDto save(TrackingPlanDto trackingPlanDto) throws DuplicateValueException {
        TrackingPlan trackingPlan = new TrackingPlan();
        trackingPlan.setDisplayName(trackingPlanDto.getDisplayName());
        trackingPlan.setRules(new ArrayList<>());
        List<Events> list = trackingPlanDto.getRules().getEvents();
        for(Events events:list) {
            Events savedEvent = eventsService.save(events);
            trackingPlan.getRules().add(savedEvent.get_id());
        }
        TrackingPlan savedTrackingPlan = trackingPlanRepository.save(trackingPlan);
        return getTrackingPlanDto(savedTrackingPlan);
    }

    @Override
    public TrackingPlanDto updateName(String id, String trackingPlanName) throws NotFoundException {
        TrackingPlan existingTrackingPlan = findTrackingPlanById(id);
        if(existingTrackingPlan == null){
            throw new NotFoundException("Tracking Plan not found for the given Id.");
        }
        existingTrackingPlan.setDisplayName(trackingPlanName);
        TrackingPlan updatedTrackingPlan = trackingPlanRepository.save(existingTrackingPlan);
        return getTrackingPlanDto(updatedTrackingPlan);
    }

    @Override
    public TrackingPlanDto addExistingEventsToTrackingPlan(String id, String eventsName) throws NotFoundException {
        TrackingPlan existingTrackingPlan = findTrackingPlanById(id);
        if(existingTrackingPlan == null){
            throw new NotFoundException("Tracking Plan not found for the given Id.");
        }
        Events events = eventsService.findByName(eventsName);
        if(events == null){
            throw new NotFoundException("Event not found for the given Id.");
        }
        if(!existingTrackingPlan.getRules().contains(events.get_id())) {
            existingTrackingPlan.getRules().add(events.get_id());
            TrackingPlan updatedTrackingPlan = trackingPlanRepository.save(existingTrackingPlan);
            return getTrackingPlanDto(updatedTrackingPlan);
        } else {
            return getTrackingPlanDto(existingTrackingPlan);
        }
    }

    @Override
    public void deleteById(String id) {
        trackingPlanRepository.deleteById(id);
    }

    @Override
    public TrackingPlanDto findById(String id) {
        TrackingPlan trackingPlan = findTrackingPlanById(id);
        if(trackingPlan == null){
            return null;
        }
        return getTrackingPlanDto(trackingPlan);

    }

    private TrackingPlan findTrackingPlanById(String id){
        Optional<TrackingPlan> trackingPlanOptional = trackingPlanRepository.findById(id);
        return trackingPlanOptional.orElse(null);
    }

    private TrackingPlanDto getTrackingPlanDto(TrackingPlan trackingPlan) {
        if(trackingPlan == null){
            return null;
        }
        TrackingPlanDto trackingPlanDto = new TrackingPlanDto();
        trackingPlanDto.setDisplayName(trackingPlan.getDisplayName());
        EventsDto eventsDto = new EventsDto();
        eventsDto.setEvents(new ArrayList<>());
        for(String eventsId: trackingPlan.getRules()){
            eventsDto.getEvents().add(eventsService.findById(eventsId));
        }
        trackingPlanDto.setRules(eventsDto);
        return trackingPlanDto;
    }

    @Override
    public List<TrackingPlanDto> findAll() {
        List<TrackingPlan> trackingPlanList = trackingPlanRepository.findAll();
        List<TrackingPlanDto> trackingPlanDtoList = new ArrayList<>();
        if(trackingPlanList.isEmpty()){
            return trackingPlanDtoList;
        }
        for(TrackingPlan trackingPlan: trackingPlanList){
            TrackingPlanDto trackingPlanDto = getTrackingPlanDto(trackingPlan);
            trackingPlanDtoList.add(trackingPlanDto);
        }
        return trackingPlanDtoList;
    }
}
