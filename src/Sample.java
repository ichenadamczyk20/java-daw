public interface Sample {

    Sample adjustGain(double x);

    Sample superpose(double x1, Sample sample2, double x2);

    Sample timeStretch(double x);

    Sample pitchShift(double x);

    Sample speedShift(double x);

    Sample resample(int sampleRate);

    Sample[] split (int[] times);

    double length();

    int sampleRate();

    public double[] rawDoubleCode();

    void play(double x1, double x2);

    void play();

}