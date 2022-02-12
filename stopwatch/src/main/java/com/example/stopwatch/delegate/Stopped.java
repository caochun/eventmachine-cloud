package com.example.eventmachine.delegate;

import com.example.eventmachine.StateDelegate;
import com.example.eventmachine.StopWatch;
import org.springframework.stereotype.Component;

@Component
public class Stopped extends StateDelegate<StopWatch> {

    public Stopped() {
        super("stopped");
    }

    @Override
    public void onState(StopWatch stopWatch) {
        System.out.println("stopped");
        stopWatch.doStopped();
    }
}
