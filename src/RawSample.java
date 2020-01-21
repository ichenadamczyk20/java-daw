import java.util.Arrays;
public class RawSample implements Sample {  //Rename to Raw Sample, add interface for Sample
    private double[] doubleCode;
    private int rate;
    private double length;

    public RawSample(double[] rawDoubleCode, int sampleRate) {
        doubleCode = rawDoubleCode;
        rate = sampleRate;
        length = ((double) rawDoubleCode.length) / sampleRate;
    }

    public RawSample(double givenLength, int sampleRate) {
        doubleCode = new double[(int) length * sampleRate];
        rate = sampleRate;
        length = givenLength;
    }

    public RawSample(FourierSample fourierSample) {

    }

    public int sampleRate() {
        return rate;
    }

    public double length() {
        return length;
    }

    public double[] rawDoubleCode() {
        return doubleCode;
    }

    public RawSample adjustGain(double x) {
        if (Math.abs(x) > 1) System.out.println("Warning: normalization by greater than one! may lose quality");
        double[] newDoubleCode = new double[doubleCode.length];
        for (int i = 0; i < doubleCode.length; i++) newDoubleCode[i] = x * doubleCode[i];
        return new RawSample(newDoubleCode, rate);
    }

    public RawSample superpose(double x1, Sample sample, double x2) {
        RawSample sample2 = (RawSample) sample;
        if (Math.abs(x1 + x2) > 1)
            System.out.println("Warning: normalization by greater than one! may lose quality");
        if (sample2.sampleRate() != rate) sample2 = sample2.resample(rate);
        if (Math.abs(sample2.length() - length) >= 1e-15)
            System.out.println("Warning: length of samples do not match!");
        double[] doubleCode2 = sample2.rawDoubleCode();
        double changedLength;
        double[] newDoubleCode;
        if (doubleCode.length < doubleCode2.length) {
            changedLength = doubleCode.length;
            newDoubleCode = doubleCode;
        } else {
            changedLength = doubleCode2.length;
            newDoubleCode = doubleCode2;
        }
        for (int i = 0; i < changedLength; i++) newDoubleCode[i] = doubleCode[i] * x1 + doubleCode2[i] * x2;

        return new RawSample(newDoubleCode, rate);
    }

    public RawSample resample(int sampleRate) {
        if (rate == sampleRate) {
            return new RawSample(doubleCode, rate);
        //} else if (rate < sampleRate) {
            //Not easy to do accurately
            //return new RawSample(doubleCode, rate);
        } else {
            int codeLength = doubleCode.length;
            double[] newDoubleCode = new double[codeLength * sampleRate / rate];
            int i = 0;
            double j = 0;
            while (j < codeLength && i < newDoubleCode.length) {
                newDoubleCode[i] = doubleCode[(int) j];
                j = j + ((double) rate) / ((double) sampleRate)));
                i += 1;
            }
            return new RawSample(newDoubleCode, sampleRate);
        }
    }

    public RawSample timeStretch(double x) {

    }

    public RawSample pitchShift(double x) {
        return new RawSample(doubleCode, rate);
    }

    public RawSample speedShift(double x) {
        if (x == 0) {
            System.out.println("Warning: speed-shifting by 0 -> length 0 sample.");
            return new RawSample(new double[0], rate);
        } else if (x < 0) {
            double[] newDoubleCode = new double[(int) (-1 * doubleCode.length / x)];
            int i = newDoubleCode.length - 1;
            double j = 0;
            while (j < doubleCode.length && i >= 0) {
                // x = -0.5:
                newDoubleCode[i] = doubleCode[(int) j];
                j -= x;
                i -= 1;
            }
            return new RawSample(newDoubleCode, rate);
        } else {
            double[] newDoubleCode = new double[(int) (doubleCode.length / x)];
            int i = 0;
            double j = 0;
            while (j < doubleCode.length && i < newDoubleCode.length) {
                // x = -0.5:
                newDoubleCode[i] = doubleCode[(int) j];
                j += x;
                i += 1;
            }
            return new RawSample(newDoubleCode, rate);
        }
    }

    public RawSample[] split(int[] times) {
        RawSample[] RawSamples = new RawSample[times.length];
        RawSamples[0] = new RawSample(Arrays.copyOfRange(doubleCode, 0, times[0]), rate);
        for (int i = 1; i < times.length - 1; i++) {
            RawSamples[i] = new RawSample(Arrays.copyOfRange(doubleCode, times[i-1], times[i]), rate);
        }
        RawSamples[times.length - 1] = new RawSample(Arrays.copyOfRange(doubleCode, times[i], doubleCode.length), rate);
        return RawSamples;
    }

    public void play(double x1, double x2) {

    }

    public void play() {

    }
}