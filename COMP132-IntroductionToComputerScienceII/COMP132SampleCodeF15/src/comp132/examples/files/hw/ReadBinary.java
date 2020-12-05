package comp132.examples.files.hw;

import java.io.*;

public class ReadBinary {
    public static void main(String[] args) {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("HWProb.file"));
            int num = dis.readInt();
            System.out.println(num);
        }
        catch (IOException e) {
            System.out.println("Error reading file HWProb.file.");
            System.exit(-1);
        }
    }
}
