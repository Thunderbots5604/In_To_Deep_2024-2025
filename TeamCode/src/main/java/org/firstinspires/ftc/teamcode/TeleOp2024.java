package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import org.firstinspires.ftc.teamcode.Intake;

@TeleOp(name = "Alpha", group = "competition")
public class TeleOp2024 extends OpMode {
    DriveTrain drive;
    Arm arm;
    Slide slide;
    ArmServo1 armServo1;
    Claw claw1;
    Claw claw2;
    Claw claw3;
    Claw claw4;

    //Intake intake;

    boolean doneInit = false;

    boolean pastBack = false;
    //boolean currentBack;
    boolean pastA = false;
    boolean currentA;
    boolean pastY = false;
    boolean currentY;
    boolean pastX = false;
    boolean currentX;
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
    boolean pastBumpered = false;
    boolean currentRightBumper = false;
    boolean currentLeftBumper = false;
    boolean rightBumperValue = false;
    boolean leftBumperValue = false;

//    boolean pastServo1ed = false;

    boolean holdArm = false;

    @Override
    public void start() {
        while(!doneInit){}
        claw1.open();
        claw2.close();
        claw3.close();
        claw4.close();
        arm.setZeroPosition();
        slide.setZeroPosition();

        //intake.setState("off");
    }

    @Override
    public void init() {
        drive = new DriveTrain(hardwareMap, "fl", "fr", "bl", "br");
        arm = new Arm(hardwareMap, "am1", 10, 1100, 0.05);
        slide = new Slide(hardwareMap, "am2", 10, 1100, 0.05);
//        armServo1 = new ArmServo1(hardwareMap, "as1", 1, -1);
        claw1 = new Claw(hardwareMap, "claw1", 0.2, 0.6);
        claw2 = new Claw(hardwareMap, "claw2", 50, 250);
        claw3 = new Claw(hardwareMap, "claw3", 0.4, 0);
        claw4 = new Claw(hardwareMap, "claw4", 50, 250);

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
//
//        currentD_Up = gamepad1.dpad_up;
//        if(currentD_Up && !pastD_Up) {
//            slide.movePower(true);
//            telemetry.addData("D", slide);
//        }else{
//            slide.stop();
//            telemetry.addData("STOP", slide);
//        }
//        pastD_Up = currentD_Up;
//
//        currentD_Down = gamepad1.dpad_down;
//        if(currentD_Down && !pastD_Down) {
//            slide.movePower(false);
//            telemetry.addData("D", slide);
//        }else{
//            slide.stop();
//            telemetry.addData("STOP", slide);
//        }
//        pastD_Down = currentD_Down;
// aeoifj;eoifja;oewijf
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

        rightTriggerValue = gamepad2.right_trigger;
        leftTriggerValue = gamepad2.left_trigger;
        if(rightTriggerValue > 0.5) {
            currentRightTrigger = true;

        }
        else if(leftTriggerValue > 0.5) {
            currentLeftTrigger = true;

        }

        if(currentRightTrigger) {
            arm.movePower(true);
        }
        else if(currentLeftTrigger) {
            arm.movePower(false);

        }

        if(!(currentLeftTrigger || currentRightTrigger)) {
            if(!pastTriggered) {
                arm.stop();
            }
        }

        pastTriggered = currentRightTrigger || currentLeftTrigger;
        currentRightTrigger = false;
        currentLeftTrigger = false;


        rightBumperValue = gamepad2.right_bumper;
        leftBumperValue = gamepad2.left_bumper;
        if(rightBumperValue) {
            currentRightBumper = true;

        }
        else if(leftBumperValue) {
            currentLeftBumper = true;

        }

        if(currentRightBumper) {
            slide.movePower(true);
        }
        else if(currentLeftBumper) {
            slide.movePower(false);

        }

        if(!(currentLeftBumper || currentRightBumper)) {
            if(!pastBumpered) {
                slide.stop();
            }
        }

        pastBumpered = currentRightBumper || currentLeftBumper;
        currentRightBumper = false;
        currentLeftBumper = false;

        if(gamepad2.dpad_down){ // hold arm if  dpad down is pressed
            holdArm = true;
        }

        if(holdArm){ // hold arm
            arm.carry();
        }
//
//        currentA = gamepad2.a;
//        currentY = gamepad2.y;
//        if(currentA) {
//            armServo1.open();
//            telemetry.addData("servo1up", armServo1);
//        }
//        else if(currentY) {
//            armServo1.close();
//            telemetry.addData("servo1down", armServo1);
//        }
//
//        if(!(currentA || currentY)) {
//            if(!pastServo1ed) {
//                armServo1.stop();
//                telemetry.addData("servo1stop", armServo1);
//            }
//        }
//
//        pastServo1ed = currentA || currentY;
//        currentA = false;
//        currentY = false;

        currentB = gamepad2.b;
        if(currentB && !pastB) {
            claw1.toggle(); // claw 1 is 1st servo on vertical arm

        }
        pastB = currentB;


        currentX = gamepad2.x;
        if(currentX && !pastX) {
            claw2.toggle();
        }
        pastX = currentX;


        currentY = gamepad2.y;
        if(currentY && !pastY) {
            claw3.toggle(); // claw that opens the door
        }
        pastY = currentY;

        currentA = gamepad2.a;
        if(currentA && !pastA) {
            claw4.toggle();
        }
        pastA = currentA;

        telemetry.addData("arm Position", arm.getCurrentPosition());
        telemetry.addData("arm 2 Position", slide.getCurrentPosition());

        telemetry.update();
    }

    @Override
    public void stop() {
        drive.stop();
        arm.stop();
        slide.stop();
        //intake.setState("off");
    }
}