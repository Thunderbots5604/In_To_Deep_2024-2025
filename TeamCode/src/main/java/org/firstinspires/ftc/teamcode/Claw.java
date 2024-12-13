package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;


public class Claw {
    private final double OPEN_POSITION;
    private final double CLOSE_POSITION;
    private boolean open;
    private Servo claw;
    private int position;
    private double thirdPosition;

    public Claw(HardwareMap map, String name, double openPosition, double closePosition, double thirdPosition) {
        claw = map.get(Servo.class, name);

        OPEN_POSITION = openPosition;
        CLOSE_POSITION = closePosition;
        this.thirdPosition = thirdPosition;
        position = 0;
    }

    public void toggle() {
        if(!open) {
            this.open();
        }
        else {
            this.close();
        }
    }

    public void close() {
        claw.setPosition(CLOSE_POSITION);
        open = false;
    }

    public void open() {
        claw.setPosition(OPEN_POSITION);
        open = true;
    }

    public void moveThirdPosition() {
        claw.setPosition(thirdPosition);
    }

    public void changePosition() {
        if(position == 0){
            this.open();
            position=1;
        }else if(position == 1){
            this.moveThirdPosition();
            position=2;
        } else if(position == 2){
            this.close();
            position=0;
        }
    }
//
//    public void stop() {
//        claw.setPosition(0);
//    }

    public boolean isOpen() {
        return open;
    }
}