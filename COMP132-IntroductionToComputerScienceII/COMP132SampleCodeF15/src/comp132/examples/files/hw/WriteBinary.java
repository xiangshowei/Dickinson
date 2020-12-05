package comp132.examples.files.hw;

import java.io.*;

public class WriteBinary {

    public static void main(String[] args) {
        DataOutputStream das = null;
        try {
            das = new DataOutputStream(new FileOutputStream("HWProb.file", false));
            das.writeInt(1146310478);
            das.close();
        }
        catch (IOException e) {
            System.out.println("Error writing file HWProb.file.");
            System.exit(-1);
        }
    }
}
