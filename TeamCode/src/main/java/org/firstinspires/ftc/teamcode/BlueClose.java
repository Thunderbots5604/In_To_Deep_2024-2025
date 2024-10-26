package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import org.firstinspires.ftc.teamcode.Camera;
//import org.firstinspires.ftc.teamcode.Claw;
//import org.firstinspires.ftc.teamcode.Intake;


@Autonomous(name = "BlueClose", group = "Autonomous")

public class BlueClose extends LinearOpMode{
    private AutoDriveTrain drive;
    private Boom boom;
    private Slide slide;
//    private Claw claw;
    //private Camera camera;
    private int location;
    private double[][] positions ={{0, 16, 0}, {0, 0, 0}, {-40, 0, 0}};
    private double ARM_HEIGHT;

    @Override
    public void runOpMode() {
        drive = new AutoDriveTrain(hardwareMap, "fl", "fr", "bl", "br", new double[] {Values.LATERAL_ERROR, Values.LONGITUDINAL_ERROR, Values.ANGLE_ERROR}, Values.INCHES_PER_TICK_LATERAL, Values.INCHES_PER_TICK_LONGITUDINAL, Values.RADIANS_PER_TICK);
        boom = new Boom(hardwareMap, "am1", 10, 1100, 0.05);
        slide = new Slide(hardwareMap, "am1", 10, 1100, 0.05);
//        claw = new Claw(hardwareMap, "servo", 0, 0, 0);
//        intake = new Intake(hardwareMap, "intMotor");
        //camera = new Camera(hardwareMap, telemetry, this);
        //camera.init();
        double[] targetPosition = new double[3];

        waitForStart();
        //location = camera.findRegion();
        sleep(500);

        for(int i = 0; i < positions.length; i++) {
            drive.setTargetLocation(positions[i]);
            while (!drive.moveToTargetLocation(.5)) {
                drive.updateCurrentLocation();
            }
            sleep(1000);
        }

//        arm.setTargetPosition(ARM_HEIGHT);
//        while(!arm.moveToTarget()){}
//        sleep(1000);

//        intake.setState("outputting");
//        sleep(2000);
//        intake.setState("off");
    }
}