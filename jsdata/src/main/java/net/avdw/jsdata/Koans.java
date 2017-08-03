package net.avdw.jsdata;

import com.google.common.io.Files;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class Koans {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File raw = new File("src/main/resources/raw", "koans-01.txt");
        
        String contents;
        try (BufferedReader reader = Files.newReader(raw, Charset.forName("UTF-8"))) {
            char[] buffer = new char[(int) raw.length()];
            reader.read(buffer);
            contents = new String(buffer);
        }
        
        String[] koans = contents.split("\n.*\\(Koan\\).*\n");
        for (int i = 0; i < koans.length; i++) {
            koans[i] = koans[i].replace("\n", " ");
            koans[i] = koans[i].replace("  ", " ");
            
            if (koans[i].startsWith(" ")) {
                koans[i] = koans[i].substring(1);
            }
            if (koans[i].endsWith(" ")) {
                koans[i] = koans[i].substring(0, koans[i].length());
            }
        }

        try (BufferedWriter writer = Files.newWriter(new File("src/main/resources/json", "koans.json"), Charset.forName("UTF-8"))) {
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(koans));
        }
    }
}
