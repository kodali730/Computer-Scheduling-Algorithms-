package cpusim.scheduling;

import cpusim.cpu.ProcessExecution;

import java.util.List;

/***
 Abstract layer for all non-preemptive algorithms. if there is current job - keep it going. otherwise start thinking
 ***/
public abstract class NonPreemptiveSchedulingAlgorithm implements SchedulingAlgorithm {
    @Override
    public ProcessExecution chooseJob(ProcessExecution currentJob, List<ProcessExecution> queuedProcesses) {
        if(currentJob!=null)
            return currentJob;
        if(queuedProcesses.size() == 0)
            return null;
        return chooseNewJob(queuedProcesses);
    }
    abstract ProcessExecution chooseNewJob(List<ProcessExecution> queuedProcesses);
}
