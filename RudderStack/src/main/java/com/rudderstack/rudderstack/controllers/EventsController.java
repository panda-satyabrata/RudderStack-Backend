package com.rudderstack.rudderstack.controllers;

import com.rudderstack.rudderstack.exceptions.DuplicateValueException;
import com.rudderstack.rudderstack.exceptions.NotFoundException;
import com.rudderstack.rudderstack.models.Events;
import com.rudderstack.rudderstack.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventsController {

    @Autowired
    EventsService eventsService;

    @GetMapping(value = "/{eventsId}", produces = "application/json")
    public ResponseEntity<Object> getEventsById(@PathVariable String eventsId){
        Events events = eventsService.findById(eventsId);
        if(events == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Events>> getEvents(){
        List<Events> eventsList = eventsService.findAll();
        return new ResponseEntity<>(eventsList, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> saveEvents(@RequestBody Events events) throws DuplicateValueException {
        Events savedEvents = eventsService.save(events);
        return new ResponseEntity<>(savedEvents, HttpStatus.OK);
    }

    @PutMapping(value = "/{eventsName}", produces = "application/json")
    public ResponseEntity<Events> updateEvents(@PathVariable String eventsName, @RequestBody Events events) throws NotFoundException {
        Events updatedEvents = eventsService.update(eventsName, events);
        return new ResponseEntity<>(updatedEvents, HttpStatus.OK);
    }
}
