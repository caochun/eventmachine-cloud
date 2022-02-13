package com.example.stopwatch;

import org.apache.commons.scxml2.env.AbstractStateMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class StateDelegate {

    private StreamBridge streamBridge;

    @Autowired
    public void setStreamBridge(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void onState(String t) {
        streamBridge.send("state-output", t);
    }
}
