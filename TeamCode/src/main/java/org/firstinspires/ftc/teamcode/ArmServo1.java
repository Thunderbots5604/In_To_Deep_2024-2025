package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;


public class ArmServo1 {
    private final double OPEN_POSITION;
    private final double CLOSE_POSITION;
    private boolean open;
    private Servo servo;

    public ArmServo1(HardwareMap map, String name, double openPosition, double closePosition) {
        servo = map.get(Servo.class, name);

        OPEN_POSITION = openPosition;
        CLOSE_POSITION = closePosition;
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
        servo.setPosition(0);
        open = false;
    }

    public void open() {
        servo.setPosition(1);
        open = true;
    }

    public void stop() {

        servo.setPosition(0.5);
    }

    public boolean isOpen() {
        return open;
    }
}