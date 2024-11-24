package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {

    private DcMotorEx armMotor2;

    private double positionModifier;
    private double targetPosition;
    private double powerIncrement;

    private double actualPosition;
    private double powerDecrement;


    private double armMotor2CurrentPower;

    public final double TARGET_INCREMENT;
    public final double MAX_POSITION;

    public Slide(HardwareMap map, String arm2Name, double positionIncrement, double maxPosition, double powerIncrement) {

        armMotor2 = map.get(DcMotorEx.class, arm2Name);

        armMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

        TARGET_INCREMENT = positionIncrement;
        MAX_POSITION = maxPosition;
        this.powerIncrement = powerIncrement;

        positionModifier = armMotor2.getCurrentPosition();
        targetPosition = getCurrentPosition();


        armMotor2CurrentPower = 0;
    }

    public void moveVelocity(double velocity) {
        if((velocity > 0 && getCurrentPosition() > MAX_POSITION) || (velocity < 0 && getCurrentPosition() < -50)) {

            armMotor2.setVelocity(1);
        }
        else {

            armMotor2.setVelocity(velocity);
        }
    }

    public void movePower(boolean direction){
        if(direction){
            if(this.getCurrentPosition() > -1500){
                armMotor2.setPower(-0.75);
            }
            else {
                armMotor2.setPower(0);
            }
//            armMotor2.setPower(-0.3);

        } else {
            if(this.getCurrentPosition() < 0){
                armMotor2.setPower(0.75);
            } else {
                armMotor2.setPower(0);
            }
//            armMotor2.setPower(0.3);


        }
    }
    public void lock() {
        setTargetPosition(getCurrentPosition());
    }

    public boolean moveToTarget() {

        actualPosition = getCurrentPosition();
        if (actualPosition < targetPosition + 10 && actualPosition > targetPosition - 10) {

            armMotor2.setVelocity(1);
            return true;
        } else if (actualPosition < targetPosition) {

            armMotor2.setVelocity(100);
            return false;
        } else {

            armMotor2.setVelocity(-100);
            return false;
        }
    }

    public double getCurrentPosition() {
        return armMotor2.getCurrentPosition() - positionModifier;
    }

    public void setZeroPosition() {
        positionModifier =  armMotor2.getCurrentPosition() ;
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

        armMotor2.setPower(0.05);
    }

    //Stops
    public void stop(){

        armMotor2.setPower(0);
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