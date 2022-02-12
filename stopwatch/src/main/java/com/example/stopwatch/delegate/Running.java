package com.example.eventmachine.delegate;

import com.example.eventmachine.StateDelegate;
import com.example.eventmachine.StopWatch;
import org.springframework.stereotype.Component;


@Component
public class Running extends StateDelegate<StopWatch> {

    public Running() {
        super("running");
    }

    @Override
    public void onState(StopWatch stopWatch) {
        System.out.println("running");
        stopWatch.doRunning();
    }
}
