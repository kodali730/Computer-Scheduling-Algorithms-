package cpusim.scheduling;

import cpusim.cpu.ProcessExecution;

import java.util.List;

/***
 Each scheduling algorithm should be able to pick right job to execute
 having given "current job" and list of "queued jobs"
 ***/
public interface SchedulingAlgorithm {
    ProcessExecution chooseJob(ProcessExecution currentJob, List<ProcessExecution> queuedProcesses);
}
