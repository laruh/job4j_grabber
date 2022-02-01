package ru.job4j.ood.srp.bad;

import java.io.*;
import java.nio.file.Path;

public class Reader {

    public static void read(Path path) {
        validation(path);
        try (BufferedReader in = new BufferedReader(new FileReader(String.valueOf(path)))) {
            in.lines()
                    .filter(line -> line.contains("word"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validation(Path path) {
        File file = path.toFile();
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", path));
        }
    }
}
