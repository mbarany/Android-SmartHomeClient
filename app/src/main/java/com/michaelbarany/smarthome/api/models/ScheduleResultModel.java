package com.michaelbarany.smarthome.api.models;

import java.util.List;

public class ScheduleResultModel {
    public String title;
    public List<EventModel> events;

    public static class EventModel {
        public String date;
        public String scenes;
        public String devices;
    }
}
