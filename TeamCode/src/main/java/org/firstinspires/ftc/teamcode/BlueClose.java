package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import org.firstinspires.ftc.teamcode.Camera;
import org.firstinspires.ftc.teamcode.Claw;
//import org.firstinspires.ftc.teamcode.Intake;


@Autonomous(name = "BlueClose", group = "Autonomous")

public class BlueClose extends LinearOpMode{
    private AutoDriveTrain drive;
    private Arm arm;
    private Slide slide;
    private Claw claw3;
    private Claw claw1;
    //private Camera camera;
    private int location;
    private double[][] positions ={{0, -16, 0}, {16, -18, 0}, {13,-18,0}};
    private double[][] positions1 ={{13, -24, 0}};
    private double[][] positions2 ={{13, -20, 0},{-21,-20,0}};

    private double ARM_HEIGHT;

    @Override
    public void runOpMode() {
        drive = new AutoDriveTrain(hardwareMap, "fl", "fr", "bl", "br", new double[] {Values.LATERAL_ERROR, Values.LONGITUDINAL_ERROR, Values.ANGLE_ERROR}, Values.INCHES_PER_TICK_LATERAL, Values.INCHES_PER_TICK_LONGITUDINAL, Values.RADIANS_PER_TICK);
        arm = new Arm(hardwareMap, "am1", 10, -4700, 0.05);
        claw1 = new Claw(hardwareMap, "claw1", 0.15,0.37,0.05);
        claw3 = new Claw(hardwareMap, "claw3", 0.65, 0.85,0);
//        intake = new Intake(hardwareMap, "intMotor");
        //camera = new Camera(hardwareMap, telemetry, this);
        //camera.init();
        double[] targetPosition = new double[3];

        waitForStart();
        //location = camera.findRegion();
        sleep(500);
        claw1.open();
        claw3.close();

        for(int i = 0; i < positions.length; i++) {
            drive.setTargetLocation(positions[i]);
            while (!drive.moveToTargetLocation(.5)) {
                drive.updateCurrentLocation();
            }
            sleep(1000);
        }
        ARM_HEIGHT=-2250;
        arm.setTargetPosition(ARM_HEIGHT);
        while(!arm.moveToTarget()){}
        for(int i = 0; i < positions1.length; i++) {
            drive.setTargetLocation(positions1[i]);
            while (!drive.moveToTargetLocation(.5)) {
                drive.updateCurrentLocation();
            }
            sleep(1000);
        }
        claw1.close();
        sleep(1000);
        ARM_HEIGHT=-1415;
        arm.setTargetPosition(ARM_HEIGHT);
        while(!arm.moveToTarget()){}
        sleep(750);
        claw3.open();
        sleep(1000);
        ARM_HEIGHT=-2075;
        arm.setTargetPosition(ARM_HEIGHT);
        while(!arm.moveToTarget()){}
        sleep(1000);
        for(int i = 0; i < positions2.length; i++) {
            drive.setTargetLocation(positions2[i]);
            while (!drive.moveToTargetLocation(.5)) {
                drive.updateCurrentLocation();
            }
            sleep(1000);
        }
        ARM_HEIGHT=0;
        arm.setTargetPosition(ARM_HEIGHT);
        while(!arm.moveToTarget()){}
        sleep(1000);


//        intake.setState("outputting");
//        sleep(2000);
//        intake.setState("off");
    }
}