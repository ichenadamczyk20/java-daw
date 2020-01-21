import java.util.ArrayList;

public class Project {
    private ArrayList<Track> tracks;

    public Project () {

    }

    public void toFile (String filename) {

    }

    public void fromFile (String filename) {

    }

    public void exportFile (String filename) {

    }

    public void importFile (String filename, boolean lossy) {
        String fileType = SoundFile.codec(filename);
        if (fileType == "audio/vnd.wav") {
            WaveFile file = new WaveFile (filename);
            Sample[] samples;
            if (lossy) {
                samples = file.toRawSamples();
            } else {
                samples = file.toRawSamples(); //Sample[] samples = file.toFourierSamples();
            }
            for (Sample sample: samples) {
                Track track = new Track(sample.length(), sample.sampleRate());
                track.addSample(sample, 0);
                tracks.add(track);
            }
        } else {
            System.out.println("File is not detected to be of wave encoding.");
        }

    }

    public String toString() {
        return "PROJECT";
    }

    public boolean parseCommand (String command) {
        return true;
    }

    public void mute(int track) {

    }

    public void unmute (int track) {

    }

    public void solo (int track) {

    }

    public void unsolo (int track) {

    }
}