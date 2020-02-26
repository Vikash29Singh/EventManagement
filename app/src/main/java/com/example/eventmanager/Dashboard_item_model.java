package com.example.eventmanager;

public class Dashboard_item_model {

        private String event_name, address, time;


        public Dashboard_item_model() {

        }

        public Dashboard_item_model(String event_name, String address, String time) {
            this.event_name = event_name;
            this.address = address;
            this.time = time;
        }

    public String getEvent_name() {
        return event_name;
    }
    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
