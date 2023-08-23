package com.mabeldlarek.web.service.impl;

import com.mabeldlarek.web.dto.EventDto;
import com.mabeldlarek.web.models.Club;
import com.mabeldlarek.web.models.Event;
import com.mabeldlarek.web.repository.ClubRepository;
import com.mabeldlarek.web.repository.EventRepository;
import com.mabeldlarek.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mabeldlarek.web.mapper.EventMapper.mapToEvent;
import static com.mabeldlarek.web.mapper.EventMapper.mapToEventDto;


@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;
    @Autowired
    public EventServiceImpl(ClubRepository clubRepository, EventRepository eventRepository) {
        this.clubRepository = clubRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void saveEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }
}
