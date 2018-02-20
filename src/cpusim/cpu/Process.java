package cpusim.cpu;

/***
Store basic information about Process. Represents single line in "input" file
***/
public class Process {
    private final int id;
    private final int arrive;
    private final int burst;
    private final int priority;

    public Process(int id , int arrive, int burst, int priority) {
        this.id = id;
        this.arrive = arrive;
        this.burst = burst;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getArrive() {
        return arrive;
    }

    public int getBurst() {
        return burst;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", arrive=" + arrive +
                ", burst=" + burst +
                ", priority=" + priority +
                '}';
    }
}
