import java.util.ArrayList;

public class Track {
    private ArrayList<Sample> samples;
    private ArrayList<Integer> sampleTimes;
    private ArrayList<Mark> marks;
    private boolean muted;
    private boolean soloed;
    private int intLength;
    private int rate;

    public Track() {
        //There's actually nothing to do here
        intLength = 0;
        rate = 1;
    }

    public Track(int length, int sampleRate) {

    }

    public Track(double doubleLength, int sampleRate) {

    }

    public void addSample(Sample sample, int position) {
    }

    public RawSample toRawSample() {

    }

    public FourierSample toFourierSample() {

    }

    public void play(double t1, double t2) {

    }

    public void play() {

    }

    public void clip() {

    }

    public String toString() {

    }

    public void adjustGain(double x) {

    }

    public void superpose(double x) {

    }

    public void timeStretch(double x) {

    }

    public void pitchShift(double x) {

    }

    public void speedShift(double x) {

    }

    public boolean isMute() {
        return muted;
    }

    public boolean isSolo() {
        return soloed;
    }

    public void mute() {
        muted = true;
    }

    public void unmute() {
        muted = false;
    }

    public void solo() {
        soloed = true;
    }

    public void unsolo() {
        soloed = false;
    }
}