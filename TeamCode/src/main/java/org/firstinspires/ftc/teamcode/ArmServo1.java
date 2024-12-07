package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;

public class ArmServo1 {
    private final int MAX_POSITION; // Maximum encoder count (software limit)
    private final int MIN_POSITION; // Minimum encoder count (software limit)
    private int currentPosition;    // Current encoder count
    private CRServo servo;          // Continuous Rotation Servo
    private int encoderOffset;      // Offset for zeroing encoder values

    public ArmServo1(HardwareMap map, String name, int minPosition, int maxPosition) {
        servo = map.get(CRServo.class, name);
        this.MIN_POSITION = minPosition;
        this.MAX_POSITION = maxPosition;
        this.currentPosition = 0; // Default initial position
        this.encoderOffset = 0;  // Encoder offset for recalibration
    }

    // set the current encoder position to 0
    public void zeroEncoder() {
        encoderOffset = getRawEncoder();
        currentPosition = 0;
    }


    public void movePower(boolean forward) {
        updateEncoder(); // update current position from encoder

        if (forward && currentPosition < MAX_POSITION) {
            servo.setPower(1.0);
        } else if (!forward && currentPosition > MIN_POSITION) {
            servo.setPower(-1.0);
        } else {
            stop(); // Stop movement if at limits
        }
    }


    public void stop() {
        servo.setPower(0.0);
    }

    // updates the current position based on the encoder value
    private void updateEncoder() {
        currentPosition = getRawEncoder() - encoderOffset;
    }

    //get raw encoder value
    private int getRawEncoder() {
        // Example: Replace with actual method to read encoder ticks
        return this.getCurrentPosition();
    }

    // return the current position
    public int getCurrentPosition() {
        return currentPosition;
    }
}