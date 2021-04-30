package Drawable.Timer;

import Utils.Colors;
import Utils.LabelConfigurator;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CustomTimer {

    private double x;
    private double y;

    private Label timer;
    private int hours;
    private int min;
    private int sec;

    private boolean isPlay;

    public CustomTimer(double x, double y) {
        this.x = x;
        this.y = y;

        isPlay = true;

        hours = 0;
        min = 0;
        sec = 0;
        timer = new Label(calcTime());
        timer.setTextFill(Colors.getTimerTextColor());
        configureLabel();
    }

    private void configureLabel() {
        AnchorPane.setLeftAnchor(timer, x);
        AnchorPane.setTopAnchor(timer, y);
        LabelConfigurator.configureLabelFont(timer);
    }

    private String calcTime() {
        String hoursString;
        if (hours > 0) {
            hoursString = hours + ":";
        } else {
            hoursString = "";
        }
        String minString;
        if (min < 10) {
            minString = "0" + min;
        } else {
            minString = String.valueOf(min);
        }
        String secString;
        if (sec < 10) {
            secString = "0" + sec;
        } else {
            secString = String.valueOf(sec);
        }
        return hoursString + minString + ":" + secString;
    }

    public static String calcTime(int sec) {
        int min = (int) Math.floor(sec / 60);
        sec = sec - min * 60;
        int hour = (int) Math.floor(min / 60);
        min = min - hour * 60;

        String hoursString;
        if (hour > 0) {
            hoursString = hour + ":";
        } else {
            hoursString = "";
        }
        String minString;
        if (min < 10) {
            minString = "0" + min;
        } else {
            minString = String.valueOf(min);
        }
        String secString;
        if (sec < 10) {
            secString = "0" + sec;
        } else {
            secString = String.valueOf(sec);
        }
        return hoursString + minString + ":" + secString;
    }

    public void inc() {
        if (isPlay) {
            sec++;
            if (sec == 60) {
                sec = 0;
                min++;
                if (min == 60) {
                    min = 0;
                    hours++;
                }
            }
            timer.setText(calcTime());
            configureLabel();
        }
    }

    public int getTimeSec() {
        return hours * 3600 + min * 60 + sec;
    }

    public void addToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(timer);
    }

    public String getTime() {
        return calcTime();
    }

    public void stop() {
        isPlay = false;
    }
}
