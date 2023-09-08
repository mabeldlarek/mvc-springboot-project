package com.mabeldlarek.web.controller;

import com.mabeldlarek.web.dto.ClubDto;
import com.mabeldlarek.web.dto.EventDto;
import com.mabeldlarek.web.models.Club;
import com.mabeldlarek.web.models.Event;
import com.mabeldlarek.web.models.UserEntity;
import com.mabeldlarek.web.security.SecurityUtil;
import com.mabeldlarek.web.service.ClubService;
import com.mabeldlarek.web.service.EventService;
import com.mabeldlarek.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EventController {
    private EventService eventService;
    private UserService userService;
    private ClubService clubService;

    @Autowired
    public EventController(EventService eventService, UserService userService, ClubService clubService) {
        this.eventService = eventService;
        this.userService = userService;
        this.clubService = clubService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        UserEntity user = new UserEntity();
        List<EventDto> events = eventService.findAllEvents();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/search")
    public String searchEvents(@RequestParam(value="query") String query, Model model) {
        List<EventDto> events = eventService.searchEvents(query);
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto club = eventService.findByEventId(eventId);
        model.addAttribute("event", club);
        return "events-edit";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto,
                              BindingResult result,
                              Model model) {
        if(result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "clubs-create";
        }
        eventService.saveEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId")Long eventId, Model model) {
        UserEntity user = new UserEntity();
        EventDto eventDto = eventService.findByEventId(eventId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("club", eventDto.getClub());
        model.addAttribute("user", user);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("events/{eventId}/delete")
    public String deleteClub(@PathVariable("eventId") long eventId, Model model) {
        eventService.delete(eventId);
        return "redirect:/events";
    }


}
