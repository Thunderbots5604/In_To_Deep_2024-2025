package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Boom;
import org.firstinspires.ftc.teamcode.Slide;
//import org.firstinspires.ftc.teamcode.Intake;

@TeleOp(name = "Alpha", group = "competition")
public class TeleOp2024 extends OpMode {
    DriveTrain drive;
    Boom boom;
    Slide slide;
    //Intake intake;

    boolean doneInit = false;

    boolean pastBack = false;
    //boolean currentBack;
    //boolean pastA = false;
    //boolean currentA;
    boolean pastB = false;
    boolean currentB;
    boolean pastD_Up = false;
    boolean currentD_Up;
    boolean pastD_Down = false;
    boolean currentD_Down;
    boolean pastD_Right = false;
    boolean currentD_Right;
    boolean halfSpeed;
    double multiplier;

    boolean pastTriggered = false;
    boolean currentRightTrigger = false;
    boolean currentLeftTrigger = false;
    double rightTriggerValue = 0;
    double leftTriggerValue = 0;

    @Override
    public void start() {
        while(!doneInit){}
//        claw.close();
        //intake.setState("off");
    }

    @Override
    public void init() {
        drive = new DriveTrain(hardwareMap, "fl", "fr", "bl", "br");
        boom = new Boom(hardwareMap, "am1", 10, 1100, 0.05);
        slide = new Slide(hardwareMap, "am2", 10, 1100, 0.05);
        //intake = new Intake(hardwareMap, "intMotor");
        doneInit = true;
    }

    @Override
    public void loop() {

        currentB = gamepad1.b;
        if(currentB && !pastB) {
            halfSpeed = !halfSpeed;
        }
        pastB = currentB;

        multiplier = halfSpeed ? 0.1 : 0.3;

        drive.setPowersToZero();
        drive.calculatePower(new double[] {multiplier * gamepad1.left_stick_x, -multiplier * gamepad1.left_stick_y, multiplier * gamepad1.right_stick_x});
        drive.normalizePowers();
        drive.pushPowers();

       currentD_Up = gamepad1.dpad_up;
        if(currentD_Up && !pastD_Up) {
            slide.movePower(true);
        }else{
            slide.stop();
        }
        pastD_Up = currentD_Up;

        currentD_Down = gamepad1.dpad_down;
        if(currentD_Down && !pastD_Down) {
            slide.movePower(false);
        }else{
            slide.stop();
        }
        pastD_Down = currentD_Down;
//
//        currentD_Down = gamepad1.dpad_down;
//        if(currentD_Down && !pastD_Down) {
//            intake.setState("intaking");
//        }
//        pastD_Down = currentD_Down;
//
//        currentD_Right = gamepad1.dpad_right;
//        if(currentD_Right && !pastD_Right) {
//            intake.setState("off");
//        }
//        pastD_Right = currentD_Right;
//
//        currentA = gamepad1.a;
//        if(currentA && !pastA) {
//            claw.toggle();
//        }
//        pastA = currentA;

        rightTriggerValue = gamepad1.right_trigger;
        leftTriggerValue = gamepad1.left_trigger;
        if(rightTriggerValue > 0.5) {
            currentRightTrigger = true;
        }
        else if(leftTriggerValue > 0.5) {
            currentLeftTrigger = true;
        }

        if(currentRightTrigger) {
            boom.movePower(true);
        }
        else if(currentLeftTrigger) {
            boom.movePower(false);
        }

        if(!(currentLeftTrigger || currentRightTrigger)) {
            if(!pastTriggered) {
                boom.stop();
            }
        }

        pastTriggered = currentRightTrigger || currentLeftTrigger;
        currentRightTrigger = false;
        currentLeftTrigger = false;
    }

    @Override
    public void stop() {
        drive.stop();
        boom.stop();
        slide.stop();
        //intake.setState("off");
    }
}