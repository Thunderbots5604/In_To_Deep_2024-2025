package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Arm;
//import org.firstinspires.ftc.teamcode.Camera;
//import org.firstinspires.ftc.teamcode.robotParts.Claw;
//import org.firstinspires.ftc.teamcode.robotParts.Intake;


@Autonomous(name = "TestLeft", group = "Autonomous")

public class TestLeft extends LinearOpMode{
    private AutoDriveTrain drive;
    private double[][] positions ={{0, 15, 0},{31, 15, 0},{28, -15, 0}};
    private Arm arm;
    @Override
    public void runOpMode() {
        drive = new AutoDriveTrain(hardwareMap, "fl", "fr", "bl", "br", new double[] {Values.LATERAL_ERROR, Values.LONGITUDINAL_ERROR, Values.ANGLE_ERROR}, Values.INCHES_PER_TICK_LATERAL, Values.INCHES_PER_TICK_LONGITUDINAL, Values.RADIANS_PER_TICK);
        arm = new Arm(hardwareMap, "am1", 10, -4700, 0.05);

        waitForStart();
        sleep(500);
//

        arm.setTargetPosition(-1500);
        while(!arm.moveToTarget()){}
        sleep(1000);
        for(int i = 0; i < positions.length; i++) {
            drive.setTargetLocation(positions[i]);
            while (!drive.moveToTargetLocation(.5)) {
                drive.updateCurrentLocation();
            }
            sleep(1000);
        }

        arm.setTargetPosition(-400);
        while(!arm.moveToTarget()){}

//        while (!drive.moveToTargetLocation(.5)) {
//            drive.updateCurrentLocation();
//        }
//        ElapsedTime timer = new ElapsedTime();
//        timer.reset();
//        while(timer.milliseconds() < 1000) {
//            drive.moveToTargetLocation(.35);
//        }
    }
}