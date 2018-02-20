package cpusim.cpu;

/***
Wrap around Process class to store Process's lifecycle through execution
***/
public class ProcessExecution {
    private final Process process;
    private Integer startedAt;
    private Integer finishedBefore;
    private int progress = 0;

    public ProcessExecution(Process process) {
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

    public void work(int currentClock){
        progress++;
        if (startedAt == null)
            startedAt = currentClock;
        if (isCompleted())
            finishedBefore = currentClock  + 1;
    }

    public boolean isCompleted(){
        return progress >= process.getBurst();
    }

    public boolean arrivesNow(int currentClock){
        return (this.process.getArrive() == currentClock);
    }

    @Override
    public String toString() {
        return "ProcessExecution{" +
                "process=" + process +
                ", startedAt=" + startedAt +
                ", finishedBefore=" + finishedBefore +
                ", progress=" + progress +
                '}';
    }
}
