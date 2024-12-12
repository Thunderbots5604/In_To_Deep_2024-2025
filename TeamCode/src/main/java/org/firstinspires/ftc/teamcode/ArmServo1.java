package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;

public class ArmServo1 {
    private final int MAX_POSITION; // Maximum encoder count (software limit)
    private final int MIN_POSITION; // Minimum encoder count (software limit)
    private int currentPosition;    // Current encoder count
    private CRServo servo;
    private int encoderOffset;      // Offset for zeroing encoder values

    public ArmServo1(HardwareMap map, String name, int minPosition, int maxPosition) {
        servo = map.get(CRServo.class, name);
        this.MIN_POSITION = minPosition;
        this.MAX_POSITION = maxPosition;
        this.currentPosition = 0;
        this.encoderOffset = 0;
    }

    public void zeroEncoder() {
        currentPosition = 0;
    }


    public void movePower(boolean forward) {
//        updateEncoder(); // update current position from encoder

        if (forward ) { //&& currentPosition < MAX_POSITION
            servo.setPower(-0.2);
            addTick();
        } else if (!forward ) { //&& currentPosition > MIN_POSITION
            servo.setPower(0.2);
            subTick();
        } else {
            stop(); // Stop movement if at limits
        }
    }


    public void stop() {
        servo.setPower(0.0);
    }

    public void addTick() {
        currentPosition++;
    }

    public void subTick() {
        currentPosition--;
    }

    public int getCurrentPosition(){
        return currentPosition;
    }

}