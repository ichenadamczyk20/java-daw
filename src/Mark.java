public class Mark {
    private int intTime;
    private double realTime;
    private String label;
    public Mark (int time, String name, int sampleRate) {
        intTime = time;
        label = name;
        realTime = time / sampleRate;
    }

    public Mark (double time, String name, int sampleRate) {
        realTime = time;
        label = name;
        intTime = (int) (time * sampleRate);
    }

    public int getIntTime () {
        return intTime;
    }

    public double getRealTime() {
        return realTime;
    }

    public String getLabel() {
        return label;
    }
}
