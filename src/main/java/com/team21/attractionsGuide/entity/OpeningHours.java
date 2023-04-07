package com.team21.attractionsGuide.entity;

import java.util.ArrayList;
import java.util.List;

public class OpeningHours {
    private boolean open_now;
    List<String> weekday_text = new ArrayList<>();

    OpeningHours(boolean open_now, List<String> weekday_text) {
        this.open_now = open_now;
        this.weekday_text = weekday_text;
    }

    OpeningHours() {

    }

    public boolean getOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }

    public void setWeekday_text(List<String> weekday_text) {
        this.weekday_text = weekday_text;
    }

    public List<String> getWeekday_text() {
        return weekday_text;
    }
}
