import java.io.File;
import java.util.Arrays;
import static java.lang.Math.toIntExact;

public class WaveFile implements SoundFile {

    private double[][] doubleCodes;
    private WavFile wavFile;
    private String fileName;

    public WaveFile(String filename) {
        fileName = filename;
        try {
            wavFile = WavFile.openWavFile(new File(filename));
            int numChannels = wavFile.getNumChannels();
            int numFrames = toIntExact(wavFile.getNumFrames()); // add error handling for too large file
            doubleCodes = new double[numChannels][numFrames];
            wavFile.readFrames(doubleCodes, numFrames); // add buffer
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public WaveFile (double[][] givenDoubleCodes, String filename, int sampleRate) {
        fileName = filename;
        doubleCodes = givenDoubleCodes;
        try {
            WavFile wavFile = WavFile.newWavFile(new File(filename), doubleCodes.length, doubleCodes[0].length, givenDoubleCodes.length, sampleRate);
            wavFile.writeFrames(givenDoubleCodes, givenDoubleCodes[0].length);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public long fileLength() {
        return wavFile.getNumFrames();
    }

    public int sampleRate() {
        return toIntExact(wavFile.getSampleRate());
    }

    public String name() {
        return fileName;
    }

    public String toString() {
        return wavFile.display();
    }

    public int channels() {
        return wavFile.getNumChannels();
    }

    public RawSample[] toRawSamples() {
        RawSample[] rawSamples = new RawSample[channels()];
        for (int i = 0; i < channels(); i++) {
            rawSamples[i] = new RawSample (doubleCodes[i], sampleRate());
        }
        return rawSamples;
    }

    public RawSample[] toRawSamples(int time1, int time2) {
        RawSample[] rawSamples = new RawSample[channels()];
        for (int i = 0; i < channels(); i++) {
            rawSamples[i] = new RawSample (Arrays.copyOfRange(doubleCodes[i], time1, time2), sampleRate());
        }
        return rawSamples;
    }

    public FourierSample[] toFourierSamples() {
        FourierSample[] fourierSamples = new FourierSample[channels()];
        for (int i = 0; i < channels(); i++) {
            fourierSamples[i] = new FourierSample (doubleCodes[i], sampleRate());
        }
        return fourierSamples;

    }

    public FourierSample[] toFourierSamples(int time1, int time2) {
        FourierSample[] fourierSamples = new FourierSample[channels()];
        for (int i = 0; i < channels(); i++) {
            fourierSamples[i] = new FourierSample (Arrays.copyOfRange(doubleCodes[i], time1, time2), sampleRate());
        }
        return fourierSamples;
    }
}
