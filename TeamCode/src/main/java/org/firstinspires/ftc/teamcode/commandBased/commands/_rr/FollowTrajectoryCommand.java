package org.firstinspires.ftc.teamcode.commandBased.commands._rr;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.commandBased.classes.triggers.TriggerCommandBase;
import org.firstinspires.ftc.teamcode.commandBased.subsystems.AutoDrivetrainSubsystem;

public class FollowTrajectoryCommand extends TriggerCommandBase {

    private final AutoDrivetrainSubsystem drive;
    private final Trajectory traj;

    public FollowTrajectoryCommand(AutoDrivetrainSubsystem drive, Trajectory traj) {
        this.drive = drive;
        this.traj = traj;
    }

    @Override
    public void initialize() {
        drive.followTrajectory(traj);
    }

    @Override
    public void execute() {
        drive.update();
    }

    @Override
    public boolean isFinished() {
        return !drive.isBusy();
    }

    @Override
    public boolean isTriggered() {
        return true;
    }

}