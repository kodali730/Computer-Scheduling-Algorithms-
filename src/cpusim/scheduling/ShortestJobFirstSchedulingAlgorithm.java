package cpusim.scheduling;

import cpusim.cpu.ProcessExecution;
import java.util.List;

/***
 Shortest process preferred. Arrival used as tiebreak
 ***/
public class ShortestJobFirstSchedulingAlgorithm extends NonPreemptiveSchedulingAlgorithm {
    @Override
    ProcessExecution chooseNewJob(List<ProcessExecution> queuedProcesses) {
        ProcessExecution processExecution = null;
        for (ProcessExecution queuedProcess : queuedProcesses) {
            if(processExecution == null){
                processExecution = queuedProcess;
                continue;
            }

            if(queuedProcess.getProcess().getBurst() < processExecution.getProcess().getBurst()){
                processExecution = queuedProcess;
                continue;
            }
            if(queuedProcess.getProcess().getBurst() == processExecution.getProcess().getBurst()){
                if(queuedProcess.getProcess().getArrive() < processExecution.getProcess().getArrive()){
                    processExecution = queuedProcess;
                }
            }
        }
        return processExecution;
    }
}
