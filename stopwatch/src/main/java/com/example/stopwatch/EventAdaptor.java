package com.example.stopwatch;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("onEvent")
public class EventAdaptor implements Consumer<String> {

    private StopWatch stopWatch;

    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    @Override
    public void accept(String s) {
//        System.out.println("eventAdaptor");
        if (this.stopWatch != null) {
            this.stopWatch.fireEvent(s);
        }
    }
}
