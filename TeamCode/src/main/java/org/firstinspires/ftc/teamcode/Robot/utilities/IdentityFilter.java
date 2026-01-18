package org.firstinspires.ftc.teamcode.Robot.utilities;

public class IdentityFilter implements BasicFilter {
    double lastValue = 0.0;

    @Override
    public void update(double val) {
        lastValue = val;
    }

    @Override
    public double getFilteredOutput() {
        return lastValue;
    }

    @Override
    public void reset() {
        lastValue = 0.0;
    }


}
