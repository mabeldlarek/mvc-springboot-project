package com.mabeldlarek.web.mapper;

import com.mabeldlarek.web.dto.EventDto;
import com.mabeldlarek.web.models.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .createdOn(eventDto.getCreatedOn())
                .updateOn(eventDto.getUpdateOn())
                .build();
        return  event;
    }

    public static EventDto mapToEventDto(Event event) {
        EventDto eventDto = EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .createdOn(event.getCreatedOn())
                .updateOn(event.getUpdateOn())
                .build();
        return eventDto;
    }

}
