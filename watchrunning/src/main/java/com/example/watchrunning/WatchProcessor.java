package com.example.watchrunning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class WatchProcessor {

    public static final Logger log = LoggerFactory.getLogger(WatchProcessor.class);


    private WatchChannels watchChannels;

    @Autowired
    public WatchProcessor(WatchChannels watchChannels) {
        this.watchChannels = watchChannels;
    }


    @StreamListener(WatchChannels.APPLICATIONS_IN)
    public void checkAndSortLoans(String str) {
        log.info(str);
    }

}
