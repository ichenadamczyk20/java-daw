import java.util.ArrayList;

public class Project {
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public Project () {

    }

//    I'll add these at a later time. . .
//    public Project (String projectFilename) {
//
//    }
//
//    public void exportFile (String filename) {
//
//    }

    public void importFile (String filename, boolean lossy) {
        String fileType = SoundFile.codec(filename);
        if (fileType.startsWith("audio/vnd.wav")) {
            System.out.println("File is of type: " + fileType);
            WaveFile file = new WaveFile (filename);
            Sample[] samples;
            if (lossy) {
                samples = file.toRawSamples();
            } else {
                samples = file.toRawSamples(); //Sample[] samples = file.toFourierSamples();
            }
            int i = 0;
            for (Sample sample: samples) {
                Track track = new Track(sample.length(), sample.sampleRate());
                track.addSample(sample, 0);
                tracks.add(track);
            }
        } else {
            System.out.println("File is not detected to be of wave encoding (and this program can't have it any other wav).");
        }

    }

    public String toString() {
        String str = "";
        for (int i = 0; i < tracks.size(); i++) {
            str += "Track #" + i + "\n";
            str += tracks.get(i).toString() + "\n\n";
        }
        return str;
    }

    public void play () {
        RawSample rawSample = tracks.get(0).toRawSample().adjustGain(1 / (double) tracks.size());
        for (int i = 0; i < tracks.size(); i++) {
            rawSample = rawSample.superpose(1, tracks.get(i).toRawSample(), 1 / (double) tracks.size());
        }
        rawSample.play();
    }

    public void play (String m1, String m2) {
        RawSample rawSample = tracks.get(0).toRawSample().adjustGain(1 / (double) tracks.size());
        for (int i = 0; i < tracks.size(); i++) {
            rawSample = rawSample.superpose(1, tracks.get(i).toRawSample(), 1 / (double) tracks.size());
        }
        //rawSample.play();
    }

    public boolean parseCommand (String command) {
        try {
            if (command.startsWith("i")) {
                String filename = command.split(" ")[1];
                importFile(filename, false);
            } else if (command.startsWith("v")) {
                System.out.println(this);
            } else if (command.startsWith("p")) {
                if (command.split(" ").length > 1) {
                    String m1 = command.split(" ")[1];
                    String m2 = command.split(" ")[2];
                    play(m1, m2);
                } else {
                    play();
                }
            } /*else if (command.startsWith("mt")) {
                mute(Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("umt")) {
                unmute(Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("st")) {
                solo(Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("ust")) {
                unsolo(Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("c") || command.startsWith("C")) {
                int track = Integer.parseInt(command.split(" ")[1]);
                String m1 = command.split(" ")[2];
                String m2 = command.split(" ")[3];
                if (command.startsWith("c")) {
                    tracks.get(track).clip(m1, m2);
                } else {
                    tracks.get(track).remove(m1, m2);
                }
            } else if (command.startsWith("e")) {
                // to be done later. . .
            } else if (command.startsWith("ps")) {
                int track = Integer.parseInt(command.split(" ")[1]);
                double x = Double.parseDouble(command.split(" ")[2]);
                String m1 = command.split(" ")[3];
                String m2 = command.split(" ")[4];
                tracks.get(track).pitchShift(x, m1, m2);
            } else if (command.startsWith("ts")) {
                int track = Integer.parseInt(command.split(" ")[1]);
                double x = Double.parseDouble(command.split(" ")[2]);
                String m1 = command.split(" ")[3];
                String m2 = command.split(" ")[4];
                tracks.get(track).timeStretch(x, m1, m2);
            } else if (command.startsWith("ss")) {
                int track = Integer.parseInt(command.split(" ")[1]);
                double x = Double.parseDouble(command.split(" ")[2]);
                String m1 = command.split(" ")[3];
                String m2 = command.split(" ")[4];
                tracks.get(track).speedShift(x, m1, m2);
            } else if (command.startsWith("g")) {
                int track = Integer.parseInt(command.split(" ")[1]);
                double x = Double.parseDouble(command.split(" ")[2]);
                String m1 = command.split(" ")[3];
                String m2 = command.split(" ")[4];
                tracks.get(track).adjustGain(x, m1, m2);
            } */else if (command.startsWith("q")) {
                return false;
            } else {
                //System.out.println("c [t] [m1] [m2]  : removes track from marks m1 to m2");
                //System.out.println("C [t] [m1] [m2]  : removes track and moves it to fill in gap");
                //  System.out.println("e [file]         : exports playback as .wav file to [file]");
                //System.out.println("g [t][x][m1][m2] : adjusts gain of track t by factor x from marks m1 to m2");
                System.out.println("h                : displays help screen");
                System.out.println("i [file]         : imports .wav file as new track");
                //System.out.println("m [name]         : marks the tracks (name must be three characters, unique)");
                //System.out.println("mt [t]           : mutes a track");
                //System.out.println("p [m1] [m2]      : plays the tracks, from marks m1 to m2"); //MAKE SURE TO HAVE CONFIRMATION IF LENGTH IS > 10 seconds.
                //  System.out.println("P [m1] [m2]      : displays the playback");
                //System.out.println("ps [t][x][m1][m2]: pitch-shifts tracks by a ratio of x");
                System.out.println("q                : exit the program (without saving)");
                //  System.out.println("s [file]         : stores project state");
                //System.out.println("ss [t][x][m1][m2]: speed-shifts tracks by a factor of x");
                //System.out.println("st [t]           : soloes a track");
                //System.out.println("ts [t][x][m1][m2]: time-stretches tracks by a factor of x");
                //  System.out.println("u                : undoes previous action");
                //System.out.println("umt [t]          : unmutes a track");
                //System.out.println("ust [t]          : unsoloes a track");
                System.out.println("v                : shows schematic of tracks, labels, and marks");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
    }

    public void mute(int track) {
        tracks.get(track).mute();
    }

    public void unmute (int track) {
        tracks.get(track).unmute();
    }

    public void solo (int track) {
        tracks.get(track).solo();
    }

    public void unsolo (int track) {
        tracks.get(track).unsolo();
    }
}