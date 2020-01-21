import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;

public interface SoundFile {

    long fileLength();

    int sampleRate();

    String name();

    static String codec(String filename) {
        try {
            Path path = new File(filename).toPath();
            String mimeType = Files.probeContentType(path);
            return mimeType;
        } catch (Exception e) {
            System.out.println(e);
            return "-1";
        }
    }

    String toString();

    int channels();

    RawSample[] toRawSamples();

    RawSample[] toRawSamples(int time1, int time2);

    FourierSample[] toFourierSamples();

    FourierSample[] toFourierSamples(int time1, int time2);

}
