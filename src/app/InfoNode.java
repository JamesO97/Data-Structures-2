package app;


public class InfoNode {
    
    int run;
    int arrayLength;
    double dist;
    long runTime;
    String sortName;

    public InfoNode(int run, int arrayLength, double dist, long runTime, String sortName) {
        this.run = run;
        this.arrayLength = arrayLength;
        this.dist = dist;
        this.runTime = runTime;
        this.sortName = sortName;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
    
    
}
