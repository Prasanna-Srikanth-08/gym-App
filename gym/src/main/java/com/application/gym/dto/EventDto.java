package com.application.gym.dto;

import com.application.gym.entity.Event;
import com.application.gym.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class EventDto {
    private int eventId;
    private String eventName;
    private String description;
    private LocalDateTime eventDateTime;

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

    public static Event prepareEvent(EventDto eventDto) {
        Event event = new Event();
        event.setEventId(eventDto.getEventId());
        event.setEventName(eventDto.getEventName());
        event.setDescription(eventDto.getDescription());
        event.setEventDateTime(eventDto.getEventDateTime());
        event.setParticipants(eventDto.getParticipants());
        return event;
    }
}
