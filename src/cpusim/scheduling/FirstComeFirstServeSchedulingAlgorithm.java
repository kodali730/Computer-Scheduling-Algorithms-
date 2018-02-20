package cpusim.scheduling;

import cpusim.cpu.ProcessExecution;

import java.util.List;

/***
 First Come - FirstServe scheduling algorithm.
 Process with smallest arrival time is chosen.
***/
public class FirstComeFirstServeSchedulingAlgorithm extends NonPreemptiveSchedulingAlgorithm {
    @Override
    ProcessExecution chooseNewJob(List<ProcessExecution> queuedProcesses) {
        ProcessExecution processExecution = null;
        for (ProcessExecution queuedProcess : queuedProcesses) {
            if(processExecution == null){
                processExecution = queuedProcess;
                continue;
            }
            if(queuedProcess.getProcess().getArrive() < processExecution.getProcess().getArrive()){
                processExecution = queuedProcess;
                continue;
            }
        }
        return processExecution;
    }
}
