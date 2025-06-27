package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.Event;
import com.bodiva.wisdomacademy.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Long id, Event updated) {
        return eventRepository.findById(id)
            .map(existing -> {
                existing.setTitle(updated.getTitle());
                existing.setDescription(updated.getDescription());
                existing.setSpeaker(updated.getSpeaker());
                existing.setStartTime(updated.getStartTime());
                existing.setEndTime(updated.getEndTime());
                existing.setRegistrationLink(updated.getRegistrationLink());
                return eventRepository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
