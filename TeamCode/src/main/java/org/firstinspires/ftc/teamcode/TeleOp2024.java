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
    //Intake intake;

    boolean doneInit = false;

    boolean pastBack = false;
    //boolean currentBack;
    boolean pastA = false;
    boolean currentA;
    boolean pastY = false;
    boolean currentY;
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

    boolean holdArm = false;

    @Override
    public void start() {
        while(!doneInit){}
//       claw.close();
        //intake.setState("off");
    }

    @Override
    public void init() {
        drive = new DriveTrain(hardwareMap, "fl", "fr", "bl", "br");
        arm = new Arm(hardwareMap, "am1", 10, 1100, 0.05);
        slide = new Slide(hardwareMap, "am2", 10, 1100, 0.05);
        armServo1 = new ArmServo1(hardwareMap, "as1", 1, -1);
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
            telemetry.addData("Rpower", arm);
        }
        else if(currentLeftTrigger) {
            arm.movePower(false);
            telemetry.addData("Lpower", arm);

        }

        if(!(currentLeftTrigger || currentRightTrigger)) {
            if(!pastTriggered) {
                arm.stop();
                telemetry.addData("STOP", arm);
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
            telemetry.addData("RSpower", slide);
        }
        else if(currentLeftBumper) {
            slide.movePower(false);
            telemetry.addData("LSpower", slide);

        }

        if(!(currentLeftBumper || currentRightBumper)) {
            if(!pastBumpered) {
                slide.stop();
                telemetry.addData("STOP", slide);
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

//        if(gamepad2.a) {
//            armServo1.open();
//            telemetry.addData("servo1up", armServo1);
//        }
//        else if(gamepad2.y) {
//            armServo1.close();
//            telemetry.addData("servo1down", armServo1);
//        }else{
//            armServo1.stop();
//            telemetry.addData("servo1stop", armServo1);
//
//        }

        currentA = gamepad2.a;
        if(currentA && !pastA) {
            armServo1.toggle();
            telemetry.addData("toggle", armServo1);
        }
        else {
            armServo1.stop();
            telemetry.addData("toggleOff", armServo1);
        }
        pastA = currentA;


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