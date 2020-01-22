import java.util.Arrays;
import java.util.Scanner;

public class jdaw {
    public static void main(String[] args) {
//        WaveFile waveFile = new WaveFile("/Users/dudududuperkins/Downloads/Plextor_Synth_Bass.wav");
//        if (args.length <= 1) {
//            //instructions
//            printInstructions();
//        } else if (args[1].equals("new") || args[1].equals("open")) {
//            //begin project
//            System.out.println("new open");
//        } else if (args[1].equals("help")) {
//            //instructions
//            printInstructions();
//        } else if (args[1].equals("do")) {
//            //do one-line instruction
//            System.out.println("do");
//        } else {
//            System.out.println("args = " + Arrays.deepToString(args) + " were not recognized. 'help' for instructions");
//        }
        if (args.length > 1) {
            if (args[1].equals("help")) {
                printInstructions();
                new Project().parseCommand("h");
            }
        } else {
            System.out.print("\njdaw (h for help) > ");
            Project proj = new Project();
            Scanner scanner = new Scanner(System.in);
            while (proj.parseCommand(scanner.nextLine()))
                System.out.print("\njdaw (h for help) > ");
            scanner.close();
        }
    }
    public static void printInstructions () {
        //System.out.println("convert : ([file1] [codec1] [file2] [codec2]) Converts a sound-file to another, preserving metadata");
        //System.out.println("do      : ([command] [-file]) Executes a string of commands, [command] (and exports the result to [file])");
        System.out.println("new     : Creates a new project");
        //System.out.println("open    : ([project-file]) Opens a previously created project");
        System.out.println("help    : Prints this menu\n");
//        System.out.println("(     commands     )");
//        System.out.println("c [m1] [m2]      : removes track from marks m1 to m2");
//        System.out.println("C [m1] [m2]      : removes track and moves it to fill in gap");
//        System.out.println("e [file]         : exports playback as .wav file to [file]");
//        System.out.println("i [file]         : imports .wav file as new track");
//        System.out.println("h                : displays help screen");
//        System.out.println("p [m1] [m2]      : plays the tracks, from marks m1 to m2"); //MAKE SURE TO HAVE CONFIRMATION IF LENGTH IS > 10 seconds.
//        //System.out.println("P [m1] [m2]      : displays the playback");
//        System.out.println("mt [t]           : mutes a track");
//        //System.out.println("ps [x]           : pitch-shifts tracks by a ratio of x");
//        //System.out.println("s [file]         : stores project state");
//        //System.out.println("ss [x] [m1] [m2] : speed-shifts tracks by a factor of x");
//        System.out.println("st [t]           : soloes a track");
//        //System.out.println("ts [x] [m1] [m2] : time-stretches tracks by a factor of x");
//        //System.out.println("u                : undoes previous action");
//        System.out.println("umt [t]          : unmutes a track");
//        System.out.println("ust [t]          : unsoloes a track");
//        System.out.println("v                : shows schematic of tracks, labels, and marks");
//    }
    }
}
