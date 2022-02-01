package ru.job4j.ood.srp.bad;

import java.nio.file.Path;

public interface TextParser {
    String parse(Path path);

    void save(String string);
}
