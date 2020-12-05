package comp132.examples.files.hw;

import java.io.*;
import java.util.Scanner;

public class ReadText {

    public static void main(String[] args) {

        Scanner scr = null;
        try {
            scr = new Scanner(new FileInputStream("HWProb.file"));
            String line = scr.nextLine();
            System.out.println(line);
            scr.close();
        }
        catch (IOException e) {
            System.out.println("Unable to read file HWProb.file.");
            System.exit(-1);
        }
    }
}
