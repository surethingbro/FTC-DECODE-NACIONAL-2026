package org.firstinspires.ftc.teamcode.Robot.utilities;

public interface BasicFilter {
    public void update(double val);
    public double getFilteredOutput();
    public void reset();
}
