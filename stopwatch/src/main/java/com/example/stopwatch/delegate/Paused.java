package com.example.eventmachine.delegate;

import com.example.eventmachine.StateDelegate;
import com.example.eventmachine.StopWatch;
import org.springframework.stereotype.Component;

@Component
public class Paused extends StateDelegate<StopWatch> {


    public Paused() {
        super("paused");
    }

    @Override
    public void onState(StopWatch stopWatch) {
        System.out.println("paused");
        stopWatch.doPaused();
    }
}
