package cpusim.io;

import cpusim.cpu.ProcessExecution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Store execution history. Able to produce correct "output" file based on available data
*/
public class ProcessExecutionLog {

    private List<Integer> processIds = new ArrayList<>();

    public void registerStep(ProcessExecution processExecution){
        this.processIds.add(processExecution.getProcess().getId());
    }
    
    public List<String> requestLog(){
        String recentEntry = null;
        List<String> log = new ArrayList<>();
        Integer id = null;
        for (int i = 0; i < processIds.size(); i++) {
            if(!processIds.get(i).equals(id)){
                if(recentEntry!=null){
                    recentEntry += " " + String.valueOf(i) + " " + processIds.get(i-1);
                    log.add(recentEntry);
                }
                recentEntry = String.valueOf(i);
            }
            id = processIds.get(i);
        }
        if(recentEntry != null){
            recentEntry += " " + String.valueOf(processIds.size()) + " " + processIds.get(processIds.size()-1);
            log.add(recentEntry);
        }
        return log;
    }

    public void writeLog(String fileName){
        List<String> log = this.requestLog();
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String s : log) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
