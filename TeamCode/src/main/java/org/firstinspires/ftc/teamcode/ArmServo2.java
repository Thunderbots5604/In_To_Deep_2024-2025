package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ArmServo2 {
    private final double OPEN_POSITION;
    private final double CLOSE_POSITION;
    private boolean open;
    private Servo servo2;

    public ArmServo2(HardwareMap map, String name, double openPosition, double closePosition) {
        servo2 = map.get(Servo.class, name);

        OPEN_POSITION = openPosition;
        CLOSE_POSITION = closePosition;
    }

    public void toggle() {
        if(!open) {
            open();
        }
        else {
            close();
        }
    }

    public void close() {
        servo2.setPosition(CLOSE_POSITION);
        open = false;
    }

    public void open() {
        servo2.setPosition(OPEN_POSITION);
        open = true;
    }

    public void stop() {
        servo2.setPosition(0);
        open = false;
    }

    public boolean isOpen() {
        return open;
    }
}