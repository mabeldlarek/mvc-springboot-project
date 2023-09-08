package com.mabeldlarek.web.service;

import com.mabeldlarek.web.dto.ClubDto;
import com.mabeldlarek.web.dto.EventDto;
import com.mabeldlarek.web.models.Club;
import com.mabeldlarek.web.models.Event;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {

    public void saveEvent(Long clubId, EventDto eventDto);
    public List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void delete(long eventId);
    List<EventDto> searchEvents(String query);

}