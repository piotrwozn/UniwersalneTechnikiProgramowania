package zad2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            StringBuilder sb = new StringBuilder();
            String cp1250 = "Cp1250";
            Stream<Path> files = Files.find(Paths.get(dirName), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile() && filePath.toString().endsWith(".txt"));
            files.forEach(file -> {
                try {
                    sb.append(new String(Files.readAllBytes(file), cp1250));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Files.write(Paths.get(resultFileName), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}