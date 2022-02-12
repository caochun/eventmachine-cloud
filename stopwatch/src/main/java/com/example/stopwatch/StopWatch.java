package com.example.stopwatch;

import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.scxml2.SCXMLListener;
import org.apache.commons.scxml2.env.AbstractStateMachine;

/**
 * A SCXML document driven stop watch.
 * <p>
 * Using SCXML makes the StopWatch class simplistic; you are neither
 * managing the stopwatch "lifecycle" nor coding any "transitions",
 * that information is pulled in straight from the behavioral model
 * of the stop watch, which is encapsulated in the SCXML document
 * the constructor points to (which in turn may be generated straight
 * from the UML model).
 */
public class StopWatch extends AbstractStateMachine {

    /**
     * The events for the stop watch.
     */
    public static final String EVENT_START = "watch.start",
            EVENT_STOP = "watch.stop", EVENT_SPLIT = "watch.split",
            EVENT_UNSPLIT = "watch.unsplit", EVENT_RESET = "watch.reset";

    /**
     * The fragments of the elapsed time.
     */
    private int hr, min, sec, fract;
    /**
     * The fragments of the display time.
     */
    private int dhr, dmin, dsec, dfract;
    /**
     * The stopwatch "split" (display freeze).
     */
    private boolean split;
    /**
     * The Timer to keep time.
     */
    private Timer timer;
    /**
     * The display decorations.
     */
    private static final String DELIM = ":", DOT = ".", EMPTY = "", ZERO = "0";

    private Collection<StateDelegate> delegateList;

    public StopWatch(Collection<StateDelegate> delegateList) throws ModelException {
        super(StopWatch.class.getClassLoader().getResource("stopwatch.xml"));

        this.delegateList = delegateList;

        this.getEngine().addListener(this.getEngine().getStateMachine(), new SCXMLListener() {
            final Collection<StateDelegate> delegates = StopWatch.this.delegateList;

            @Override
            public void onEntry(EnterableState enterableState) {

                for (StateDelegate delegate : delegates) {
                    if (enterableState.getId().equals(delegate.getState())) {
                        delegate.onState(StopWatch.this);
                    }

                }
            }

            @Override
            public void onExit(EnterableState enterableState) {

            }

            @Override
            public void onTransition(TransitionTarget transitionTarget, TransitionTarget transitionTarget1, Transition transition, String s) {

            }
        });
    }

    // Each method below is the activity corresponding to a state in the
    // SCXML document (see class constructor for pointer to the document).
    public void doReset() {
        hr = min = sec = fract = dhr = dmin = dsec = dfract = 0;
        split = false;
    }

    public void doRunning() {
        split = false;
        if (timer == null) {
            timer = new Timer(true);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    increment();
                }
            }, 100, 100);
        }
    }

    public void doPaused() {
        split = true;
    }

    public void doStopped() {
        timer.cancel();
        timer = null;
    }

    public String getDisplay() {
        final String padhr = dhr > 9 ? EMPTY : ZERO;
        final String padmin = dmin > 9 ? EMPTY : ZERO;
        final String padsec = dsec > 9 ? EMPTY : ZERO;
        return padhr + dhr + DELIM + padmin + dmin + DELIM + padsec + dsec + DOT + dfract;
    }

    // used by the demonstration (see StopWatchDisplay usecase)
    public String getCurrentState() {
        return getEngine().getCurrentStatus().getStates().iterator().next().getId();
    }

    private void increment() {
        if (fract < 9) {
            fract++;
        } else {
            fract = 0;
            if (sec < 59) {
                sec++;
            } else {
                sec = 0;
                if (min < 59) {
                    min++;
                } else {
                    min = 0;
                    if (hr < 99) {
                        hr++;
                    } else {
                        hr = 0; // wrap
                    }
                }
            }
        }
        if (!split) {
            dhr = hr;
            dmin = min;
            dsec = sec;
            dfract = fract;
        }
    }

}