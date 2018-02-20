package cpusim;

import cpusim.cpu.CPU;
import cpusim.cpu.Process;
import cpusim.io.ProcessExecutionLog;
import cpusim.io.ProcessFileReader;
import cpusim.scheduling.FirstComeFirstServeSchedulingAlgorithm;
import cpusim.scheduling.PriorityHighPreemptiveAlgorithm;
import cpusim.scheduling.SchedulingAlgorithm;
import cpusim.scheduling.ShortestJobFirstSchedulingAlgorithm;

import java.util.List;
import java.util.Random;


/***

***/
public class Main {

    private static Random random = new Random();

    public static void main(String[] args) {

        String inFile = "test1.txt";
        String outFile = "results_" + inFile;

        ProcessFileReader processFileReader = new ProcessFileReader(inFile);
        processFileReader.readFile();
        List<Process> processList = processFileReader.getProcessList();


        //SchedulingAlgorithm firstComeFirstServeSchedulingAlgorithm = new FirstComeFirstServeSchedulingAlgorithm();
        SchedulingAlgorithm shortestJobFirstSchedulingAlgorithm = new ShortestJobFirstSchedulingAlgorithm();
        
        //SchedulingAlgorithm priorityHighPreemptiveAlgorithm = new PriorityHighPreemptiveAlgorithm();

        CPU cpu = new CPU(shortestJobFirstSchedulingAlgorithm,processList);
        //cpu.showCurrentProcessAndPendingQueue();

        cpu.work();
        ProcessExecutionLog processExecutionLog = cpu.getProcessExecutionLog();
        processExecutionLog.writeLog(outFile);

        List<String> log = processExecutionLog.requestLog();
        System.out.println("Execution result : ");
        for (String s : log) {
            System.out.println("  " + s);
        }

    }

}
