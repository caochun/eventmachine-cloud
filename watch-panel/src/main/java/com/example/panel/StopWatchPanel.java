package com.example.panel;

import com.example.stopwatch.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

@Component
public class StopWatchPanel extends JFrame
        implements ActionListener {


    private Image watchImage;

    public StopWatchPanel() {
        super("SCXML stopwatch");
    }

    private CommandDelegate commandDelegate;

    @Autowired
    public void setCommandDelegate(CommandDelegate commandDelegate) {
        this.commandDelegate = commandDelegate;
    }


    public void actionPerformed(final ActionEvent e) {

        final String command = e.getActionCommand();
        if (command.equals("START")) {
            if (start.getText().equals("Start")) {
                commandDelegate.sendCommand(StopWatch.EVENT_START);
                start.setText("Stop");
                split.setEnabled(true);
            } else if (start.getText().equals("Stop")) {
                commandDelegate.sendCommand(StopWatch.EVENT_STOP);
                start.setText("Reset");
                split.setEnabled(false);
            } else {
                commandDelegate.sendCommand(StopWatch.EVENT_RESET);
                start.setText("Start");
                split.setText("Split");
            }
        } else if (command.equals("SPLIT")) {
            if (split.getText().equals("Split")) {
                commandDelegate.sendCommand(StopWatch.EVENT_SPLIT);
                split.setText("Unsplit");
            } else {
                commandDelegate.sendCommand(StopWatch.EVENT_UNSPLIT);
                split.setText("Split");
            }
        }
    }

    @PostConstruct
    private void setupUI() {
        final URL imageURL = this.getClass().getClassLoader()
                .getResource("stopwatch.gif");
        final URL iconURL = this.getClass().getClassLoader()
                .getResource("stopwatchicon.gif");
        final Toolkit kit = Toolkit.getDefaultToolkit();
        watchImage = kit.createImage(imageURL);
        final Image watchIcon = kit.createImage(iconURL);
        final WatchPanel panel = new WatchPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);
        state = new JLabel();
        panel.add(state, BorderLayout.CENTER);
        pack();
        start = makeButton("START", "start, stop, reset", "Start");
        panel.add(start, BorderLayout.LINE_START);
        state = new JLabel();
        panel.add(state, BorderLayout.CENTER);
        split = makeButton("SPLIT", "split, unsplit", "Split");
        split.setEnabled(false);
        panel.add(split, BorderLayout.LINE_END);
        pack();
        setLocation(200, 200);
        setIconImage(watchIcon);
        setResizable(false);
        setSize(300, 125);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private JButton makeButton(final String actionCommand,
                               final String toolTipText, final String altText) {
        final JButton button = new JButton(altText);
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        button.setOpaque(false);
        return button;
    }

    private int hb = 0;

    Logger logger = LoggerFactory.getLogger(WatchRunningApplication.class);


    public void updateStatus(String status) {
        if (status.equals("heartbeat")) {
            logger.info(status);
        } else {
            this.state.setText(status);

        }
    }

    private StateAdaptor stateAdaptor;

    @Autowired
    public void setStateAdaptor(StateAdaptor stateAdaptor) {
        this.stateAdaptor = stateAdaptor;
    }

    @PostConstruct
    public void bindStateAdaptor() {
        this.stateAdaptor.setStopWatchPanel(this);
    }

    class WatchPanel extends JPanel {
        @Override
        public void paintComponent(final Graphics g) {
            if (watchImage != null) {
                g.drawImage(watchImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    private JLabel state;
    private JButton start, split;
    // spaces :: GridBagConstraints ;-)
    private static final String DISPLAY_PREFIX = "<html><font face=\"Courier\" color=\"maroon\"" +
            " size=\"10\"><b>&nbsp;&nbsp;&nbsp;",
            DISPLAY_SUFFIX = "</b></font></html>",
            STATE_PREFIX = "<html><font color=\"blue\" size=\"4\"" +
                    ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
            STATE_SUFFIX = "</font></html>";

}