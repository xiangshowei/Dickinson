package comp132.examples.files.hw;

import java.io.*;

public class WriteText {

    public static void main(String[] args) {
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream("HWProb.file", false));
            pw.println("OMG!");
            pw.close();
        }
        catch (IOException e) {
            System.out.println("Unable to write file HWProb.file.");
            System.exit(-1);
        }
    }
}
