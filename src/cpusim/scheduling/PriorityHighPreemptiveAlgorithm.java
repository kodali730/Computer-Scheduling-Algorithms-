package cpusim.scheduling;

import cpusim.cpu.ProcessExecution;

import java.util.List;

/**
 * Created by algis on 17.25.10.
 */
public class PriorityHighPreemptiveAlgorithm extends PreemptiveSchedulingAlgorithm {
    @Override
    ProcessExecution chooseNewJob(List<ProcessExecution> queuedProcesses) {

        ProcessExecution processExecution = null;
        if (queuedProcesses.size() > 0)
            processExecution = queuedProcesses.get(0);

        for (ProcessExecution queuedProcess : queuedProcesses) {
            if(processExecution == null || queuedProcess.getProcess().getPriority() > processExecution.getProcess().getPriority()){
                processExecution = queuedProcess;
            }
        }
        return processExecution;
    }
}
