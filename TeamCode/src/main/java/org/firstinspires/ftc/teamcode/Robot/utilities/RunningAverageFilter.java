package org.firstinspires.ftc.teamcode.Robot.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class RunningAverageFilter implements BasicFilter {

    private final double[] buffer;
    private final int windowSize;
    private int index = 0;
    private int count = 0;
    private double sum = 0.0;

    public RunningAverageFilter(int windowSize) {
        if (windowSize < 1) throw new IllegalArgumentException("windowSize must be more or equal than 1");

        this.windowSize = windowSize;
        this.buffer = new double[windowSize];
    }

     @Override
    public void update(double val) {
        if (count == windowSize) {
            sum -= buffer[index];
        } else {
            count++;
        }

        buffer[index] = val;
        sum += val;
        index = (index + 1) % windowSize;
    }

    @Override
    public double getFilteredOutput() {
        return (count == 0) ? 0.0 : sum / count;
    }

    @Override
    public void reset() {
        Arrays.fill(buffer, 0.0);
        index = 0;
        count = 0;
        sum = 0.0;
    }
}
