package org.firstinspires.ftc.teamcode.Robot.MecanumDrive.Subsystem;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class MecanumDriveSubsystem extends SubsystemBase {
   Robot robot = Robot.get();

   public Follower follower;
   public static Pose lastPose = new Pose(0,0,0);
   public static Pose blueBasket = new Pose(0,144);
   public static Pose redBasket = blueBasket.mirror();

   public MecanumDriveSubsystem(HardwareMap hardwareMap, Pose startingPose) {
       this.follower = Constants.createFollower(hardwareMap);
       follower.setStartingPose(startingPose == null ? new Pose(0,0,0) : startingPose);
       follower.update();
   }

    @Override
    public void periodic() {
        lastPose = follower.getPose();

        robot.multipleTelemetry.addData("X:", lastPose.getX());
        robot.multipleTelemetry.addData("Y:", lastPose.getY());
        robot.multipleTelemetry.addData("Velocity:", getVelocity().getMagnitude());
        robot.multipleTelemetry.addData("Heading:", Math.toDegrees(follower.getHeading()));
        robot.multipleTelemetry.addData("Angular velocity:", Math.toDegrees(getAngularVelocity()));
        follower.update();
    }

    public void setTeleOpDrive(double forward, double strafe, double rotation) {
       follower.setTeleOpDrive(forward,strafe, rotation);
    }

    public void resetHeading(double newHeading) {
       follower.setHeading(newHeading);
    }

    public Vector getVelocity() {
       return follower.getVelocity();
    }

    public double getAngularVelocity() {
       return follower.getAngularVelocity();
    }

    public Vector getAcceleration() {
       return follower.getAcceleration();
    }

    public Pose getPose() {
       return follower.getPose();
    }



}
