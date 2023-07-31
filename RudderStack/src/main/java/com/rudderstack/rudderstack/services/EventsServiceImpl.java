package com.rudderstack.rudderstack.services;

import com.rudderstack.rudderstack.exceptions.DuplicateValueException;
import com.rudderstack.rudderstack.exceptions.NotFoundException;
import com.rudderstack.rudderstack.models.Events;
import com.rudderstack.rudderstack.repos.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsServiceImpl implements EventsService{

    @Autowired
    EventsRepository eventsRepository;

    @Override
    public Events save(Events events) throws DuplicateValueException{
        if(findByName(events.getName()) != null){
            throw new DuplicateValueException("Event already exists for given name: " + events.getName());
        }
        return eventsRepository.save(events);
    }

    @Override
    public Events update(String eventsName, Events events) throws NotFoundException {
        if(findByName(events.getName()) == null){
            throw new NotFoundException("Event doesn't exists for given name: " + eventsName);
        }
        return eventsRepository.save(events);
    }

    @Override
    public void deleteById(String id) {
        eventsRepository.deleteById(id);
    }

    @Override
    public Events findById(String id) {
        Optional<Events> eventsOptional = eventsRepository.findById(id);
        return eventsOptional.orElse(null);
    }

    @Override
    public List<Events> findAll() {
        return eventsRepository.findAll();
    }

    @Override
    public Events findByName(String name){
        Optional<Events> eventsOptional = eventsRepository.findByName(name);
        return eventsOptional.orElse(null);
    }
}
