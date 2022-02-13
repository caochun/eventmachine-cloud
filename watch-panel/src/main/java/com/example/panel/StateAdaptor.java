package com.example.panel;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("stateConsumer")
public class StateAdaptor implements Consumer<String> {

    private StopWatchPanel stopWatchPanel;


    public void setStopWatchPanel(StopWatchPanel stopWatchPanel) {
        this.stopWatchPanel = stopWatchPanel;
    }

    @Override
    public void accept(String s) {
        if (this.stopWatchPanel != null) {
            this.stopWatchPanel.updateStatus(s);
        }
    }
}
