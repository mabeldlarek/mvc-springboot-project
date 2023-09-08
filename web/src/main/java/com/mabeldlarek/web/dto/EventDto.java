package com.mabeldlarek.web.dto;

import com.mabeldlarek.web.models.Club;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String photoUrl;
    @NotEmpty
    private String type;
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
    private Club club;
}
