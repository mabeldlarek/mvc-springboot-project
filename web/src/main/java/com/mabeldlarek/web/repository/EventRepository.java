package com.mabeldlarek.web.repository;

import com.mabeldlarek.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
