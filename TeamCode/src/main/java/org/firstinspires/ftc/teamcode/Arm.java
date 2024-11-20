package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    private DcMotorEx armMotor;

    private double positionModifier;
    private double targetPosition;
    private double powerIncrement;

    private double actualPosition;
    private double powerDecrement;

    private double armMotorCurrentPower;

    public final double TARGET_INCREMENT;
    public final double MAX_POSITION;

    public Arm(HardwareMap map, String armName, double positionIncrement, double maxPosition, double powerIncrement) {
        armMotor = map.get(DcMotorEx.class, armName);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        TARGET_INCREMENT = positionIncrement;
        MAX_POSITION = maxPosition;
        this.powerIncrement = powerIncrement;

        positionModifier = armMotor.getCurrentPosition();
        targetPosition = getCurrentPosition();

        armMotorCurrentPower = 0;
    }

    public void moveVelocity(double velocity) {
        if((velocity > 0 && getCurrentPosition() > MAX_POSITION) || (velocity < 0 && getCurrentPosition() < -50)) {
            armMotor.setVelocity(1);
        }
        else {
            armMotor.setVelocity(velocity);
        }
    }

    public void movePower(boolean direction){
        if(direction){
            armMotor.setPower(0.5);

        } else {
            armMotor.setPower(-0.5);

        }
    }
    public void lock() {
        setTargetPosition(getCurrentPosition());
    }

    public boolean moveToTarget() {

        actualPosition = getCurrentPosition();
        if (actualPosition < targetPosition + 10 && actualPosition > targetPosition - 10) {
            armMotor.setVelocity(1);
            return true;
        } else if (actualPosition < targetPosition) {
            armMotor.setVelocity(100);

            return false;
        } else {
            armMotor.setVelocity(-100);

            return false;
        }
    }

    public double getCurrentPosition() {
        return armMotor.getCurrentPosition() - positionModifier;
    }

    public void setZeroPosition() {
        positionModifier = armMotor.getCurrentPosition();
        targetPosition = getCurrentPosition();
    }

    public void incrementTargetPosition() {
        if(!(targetPosition > MAX_POSITION)) {
            targetPosition += TARGET_INCREMENT;
        }
    }

    public void decrementTargetPosition() {
        if(targetPosition + 50 > TARGET_INCREMENT) {
            targetPosition -= TARGET_INCREMENT;
        }
    }

    //Value set to hold
    public void hold()
    {
        armMotor.setPower(0.05);

    }

    //Stops
    public void stop(){
        armMotor.setPower(0);

    }

    public void setTargetPosition(double target) {
        targetPosition = target;
    }

    public double getTargetPosition(double target) {
        return targetPosition;
    }

    public double getModifier() {
        return positionModifier;
    }
}