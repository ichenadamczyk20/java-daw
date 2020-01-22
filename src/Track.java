import java.util.ArrayList;
import java.util.Arrays;

public class Track {
    private ArrayList<Sample> samples = new ArrayList<Sample>();
    private ArrayList<Integer> sampleTimes = new ArrayList<Integer>();
    private ArrayList<Mark> marks = new ArrayList<Mark>();
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
        intLength = length;
        rate = sampleRate;
    }

    public Track(double doubleLength, int sampleRate) {
        intLength = (int) (doubleLength * sampleRate);
        rate = sampleRate;
    }

    public void addSample(Sample sample, int position) {
        samples.add(sample);
        sampleTimes.add(position);
    }

    public RawSample toRawSample() {
        double[] doubleCode = new double[intLength];
        for (int i = 0; i < samples.size(); i++) {
            double[] sampleDoubleCode = samples.get(i).rawDoubleCode();
            for (int j = 0; j < samples.get(i).rawDoubleCode().length; j++) {
                doubleCode[j + sampleTimes.get(i)] = sampleDoubleCode[j];
            }
        }
        return new RawSample(doubleCode, rate);
    }

//    Another time
//    public FourierSample toFourierSample() {
//
//    }

    public void addMark(double time, String label) {
        marks.add(new Mark(time, label, rate));
    }

    public Mark getMark(String label) {
        for (int i = 0; i < marks.size(); i++)
            if (marks.get(i).getLabel() == label)
                return marks.get(i);
        return new Mark(0, "srt", rate);
    }

    public void removeMark (String label) {

    }

    public void play(String m1, String m2) {
        int t1 = getMark(m1).getIntTime();
        int t2 = getMark(m2).getIntTime();
        StdAudio.play(Arrays.copyOfRange(toRawSample().rawDoubleCode(), t1, t2));
    }

    public void play(double t1, double t2) {
        int time1 = (int) t1 * rate;
        int time2 = (int) t2 * rate;
        StdAudio.play(Arrays.copyOfRange(toRawSample().rawDoubleCode(), time1, time2));
    }

    public void play() {
        StdAudio.play(toRawSample().rawDoubleCode());
    }

    public void clip(double t1, double t2) {

    }

    public void clip(String m1, String m2) {

    }

    public void remove(String m1, String m2) {

    }


    public void remove(double t1, double t2) {

    }

    public String toString() {
        ArrayList<Mark> altmarks = new ArrayList<Mark>();
        if (marks.size() == 0) {
            for (int i = 0; i < 20; i++) {
                altmarks.add(new Mark(intLength * i / 20, "" + i, rate));
            }
        } else {
            altmarks = marks;
        }
        double[] rawDoubleCode = toRawSample().rawDoubleCode();
        double[] renderValues = new double[altmarks.size()]; //* 3];
        for (int i = 0; i < altmarks.size(); i++) {
            renderValues[i] = rawDoubleCode[altmarks.get(i).getIntTime()]; //change later to include maximum of sufficient block
        }
        String outputStr = "";
        for (int i = 5; i > -6; i--) {
            for (int j = 0; j < altmarks.size(); j++) {
                outputStr += "  ";
                if (rawDoubleCode[j] * 2 - 1 > (((double) i) / 5) - 0.2) {
                    outputStr += "|";
                } else {
                    outputStr += " ";
                }
                outputStr += "  ";
            }
            outputStr += "\n";
        }

        for (int i = 0; i < altmarks.size(); i++) {
            String label = altmarks.get(i).getLabel();
            outputStr += " " + label;
            for (int j = label.length(); j <= 3; j++) {
                outputStr += " ";
            }
            outputStr += " ";
        }
        return outputStr;
    }

    public void adjustGain(double x, String m1, String m2) {

    }

    public void superpose(double x, String m1, String m2) {

    }

    public void timeStretch(double x, String m1, String m2) {

    }

    public void pitchShift(double x, String m1, String m2) {

    }

    public void speedShift(double x, String m1, String m2) {

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