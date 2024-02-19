package com.application.gym.dto;

import com.application.gym.entity.Event;

import java.time.LocalDateTime;

public class EventDto {
    private int eventId;
    private String eventName;
    private String description;
    private LocalDateTime eventDateTime;

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

    public static Event prepareEvent(EventDto eventDto) {
        Event event = new Event();
        event.setEventId(eventDto.getEventId());
        event.setEventName(eventDto.getEventName());
        event.setDescription(eventDto.getDescription());
        event.setEventDateTime(eventDto.getEventDateTime());
        return event;
    }
}
