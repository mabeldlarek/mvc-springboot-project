package com.mabeldlarek.web.repository;

import com.mabeldlarek.web.models.Club;
import com.mabeldlarek.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e from Event e WHERE e.name LIKE CONCAT('%', :query, '%')")
    List<Event> searchEvents(String query);
}
