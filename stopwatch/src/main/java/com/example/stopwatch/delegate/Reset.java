package com.example.eventmachine.delegate;

import com.example.eventmachine.StateDelegate;
import com.example.eventmachine.StopWatch;
import org.springframework.stereotype.Component;

@Component
public class Reset extends StateDelegate<StopWatch> {
    public Reset() {
        super("reset");
    }

    @Override
    public void onState(StopWatch stopWatch) {
        System.out.println("reset");
        stopWatch.doReset();
    }
}
