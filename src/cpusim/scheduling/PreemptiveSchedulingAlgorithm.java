package cpusim.scheduling;

import cpusim.cpu.ProcessExecution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by algis on 17.24.10.
 */
public abstract class PreemptiveSchedulingAlgorithm implements SchedulingAlgorithm {

    @Override
    public ProcessExecution chooseJob(ProcessExecution currentJob, List<ProcessExecution> queuedProcesses) {
        List<ProcessExecution> copy = new ArrayList<>(queuedProcesses);
        if (currentJob != null)
            copy.add(0,currentJob);

        return chooseNewJob(copy);
    }

    abstract ProcessExecution chooseNewJob(List<ProcessExecution> queuedProcesses);
}
