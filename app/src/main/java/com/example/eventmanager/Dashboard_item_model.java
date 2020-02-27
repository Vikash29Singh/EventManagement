package com.example.eventmanager;

public class Dashboard_item_model {

        private String event_name, address, date, stime, etime;


        public Dashboard_item_model() {

        }

        public Dashboard_item_model(String event_name, String address, String date, String stime, String etime) {
            this.event_name = event_name;
            this.address = address;
            this.date = date;
            this.stime = stime;
            this.etime = etime;
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

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getStime() {
        return stime;
    }
    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }
    public void setEtime(String etime) {
        this.etime = etime;
    }
}
