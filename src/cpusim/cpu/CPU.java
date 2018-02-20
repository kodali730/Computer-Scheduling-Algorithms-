package cpusim.cpu;

import cpusim.io.ProcessExecutionLog;
import cpusim.scheduling.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/***
 CPU class perform simullation over list of Process objects using available SchedulingAlgorithm,
 and store results to ProcessExecutionLog
***/
public class CPU {

    private SchedulingAlgorithm schedulingAlgorithm;
    private List<ProcessExecution> futureProcesses = new ArrayList<ProcessExecution>();
    private List<ProcessExecution> queuedProcesses = new ArrayList<ProcessExecution>();
    private ProcessExecution currentProcess = null;
    private List<ProcessExecution> completedProcesses = new ArrayList<>();
    private ProcessExecutionLog processExecutionLog = new ProcessExecutionLog();
    private int step = 0;

    public CPU(SchedulingAlgorithm schedulingAlgorithm,List<Process> futureProcesses){
        this.schedulingAlgorithm = schedulingAlgorithm;
        for (Process futureProcess : futureProcesses) {
            this.futureProcesses.add(new ProcessExecution(futureProcess));
        }
    }

    public void work(){

        while (this.haveJobToDo()){
            printState("AT STEP START");
            moveFutureProcessesToQueue();
            printState("UPDATE QUEUE");
            pickCurrentJob();
            printState("CURRENT JOB PICKED");
            doWork();
            printState("WORK MADE");
            step++;

        }
    }

    private boolean haveJobToDo(){
        return (futureProcesses.size() + queuedProcesses.size() > 0 || currentProcess != null);
    }

    private void moveFutureProcessesToQueue(){
        for (ProcessExecution futureProcess : futureProcesses) {
            if(futureProcess.arrivesNow(this.step)){
                this.queuedProcesses.add(futureProcess);
            }
        }
        this.futureProcesses.removeAll(this.queuedProcesses);
    }

    private void pickCurrentJob(){
        ProcessExecution processExecution = this.schedulingAlgorithm.chooseJob(this.currentProcess,this.queuedProcesses);
        if(processExecution == null)
            return;
        if(processExecution == this.currentProcess)
            return;

        if(this.currentProcess != null)
            this.queuedProcesses.add(this.currentProcess);

        this.currentProcess = processExecution;
        this.queuedProcesses.remove(this.currentProcess);

    }

    private void doWork(){
        if(this.currentProcess == null)
            return;
        this.currentProcess.work(this.step);
        this.processExecutionLog.registerStep(this.currentProcess);
        if(this.currentProcess.isCompleted()){
            this.completedProcesses.add(this.currentProcess);
            this.currentProcess = null;
        }
    }



    private boolean printFutureQueue = false;
    private boolean printPendingQueue = false;
    private boolean printCurrentProcess = false;
    private boolean printCompletedQueue = false;

    public void showFullLog(){
        printFutureQueue = true;
        printPendingQueue = true;
        printCurrentProcess = true;
        printCompletedQueue = true;
    }

    public void showCurrentProcess(){
        printCurrentProcess = true;
    }

    public void showCurrentProcessAndPendingQueue(){
        printCurrentProcess = true;
        printPendingQueue = true;
    }


    private void printState(String caption){
        if(!printCompletedQueue && !printPendingQueue && !printCurrentProcess && !printFutureQueue)
            return;
        System.out.println(caption + "  CURRENT STEP " + this.step);

        if(printFutureQueue) {
            System.out.println("  FUTURE QUEUE " + this.futureProcesses.size() + " ITEMS");
            for (ProcessExecution futureProcess : this.futureProcesses) {
                System.out.println("    " + futureProcess);
            }
        }
        if(printPendingQueue){
            System.out.println("  PENDING QUEUE " + this.queuedProcesses.size() + " ITEMS");
            for (ProcessExecution queuedProcess: this.queuedProcesses) {
                System.out.println("    " + queuedProcess);
            }
        }
        if(printCurrentProcess)
            System.out.println("  CURRENT JOB " + this.currentProcess);

        if(printCompletedQueue) {
            System.out.println("  COMPLETED QUEUE " + this.completedProcesses.size() + " ITEMS");
            for (ProcessExecution completedProcesses : this.completedProcesses) {
                System.out.println("    " + completedProcesses);
            }
        }
    }

    public ProcessExecutionLog getProcessExecutionLog(){
        return this.processExecutionLog;
    }

}
