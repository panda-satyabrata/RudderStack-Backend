package com.rudderstack.rudderstack.services;

import com.rudderstack.rudderstack.exceptions.DuplicateValueException;
import com.rudderstack.rudderstack.exceptions.NotFoundException;
import com.rudderstack.rudderstack.models.Events;

import java.util.List;

public interface EventsService {

    Events save(Events events) throws DuplicateValueException;

    Events update(String eventsName, Events events) throws NotFoundException;

    void deleteById(String id);

    Events findById(String id);

    List<Events> findAll();

    Events findByName(String name);
}
