package cpusim.io;

import cpusim.cpu.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
Read file and create Process objects based on data in file
***/
public class ProcessFileReader {
    private String fileName;

    private int listSize;
    private int secondRowEntry1;
    private int secondRowEntry2;
    private List<Process> processList = new ArrayList<>();

    public ProcessFileReader(String fileName){
        this.fileName = fileName;
    }

    public void readFile(){
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String sCurrentLine;

            listSize = Integer.valueOf(br.readLine());
            String[] line2data = br.readLine().split(" ");
            secondRowEntry1 = Integer.valueOf(line2data[0]);
            secondRowEntry2 = Integer.valueOf(line2data[1]);

            int id = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] processData = sCurrentLine.split(" ");

                int arrive = Integer.valueOf(processData[0]);
                int burst = Integer.valueOf(processData[1]);
                int priority = Integer.valueOf(processData[2]);
                Process process = new Process(id,arrive,burst,priority);
                this.processList.add(process);
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSecondRowEntry1() {
        return secondRowEntry1;
    }

    public List<Process> getProcessList() {
        return processList;
    }
}
