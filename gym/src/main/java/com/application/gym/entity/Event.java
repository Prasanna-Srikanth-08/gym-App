package com.application.gym.entity;

import com.application.gym.dto.EventDto;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "gym_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int eventId;
    private String eventName;
    private String description;
    private LocalDateTime eventDateTime;

    @OneToMany
    @JoinColumn(name = "fk_event_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> participants;


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public static EventDto prepareEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setEventId(event.getEventId());
        eventDto.setEventName(event.getEventName());
        eventDto.setDescription(event.getDescription());
        eventDto.setEventDateTime(event.getEventDateTime());
        eventDto.setParticipants(event.getParticipants());
        return eventDto;
    }
}
