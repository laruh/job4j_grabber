package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.StringJoiner;

/**
 * Программа должна считывать текстовые файлы из системы и выдавать текст при запросе имени файла.
 * Если в кеше файла нет. Кеш должен загрузить себе данные.
 * @author Sharon Alina
 * @version 1.0
 */
public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод предназначен для загрузки содержимого файла.
     * Если в кеше файла нет. Кеш должен загрузить себе данные.
     * @param key ключ, по которому кэш должен вернуть содержимое файла.
     */
    @Override
    protected String load(String key) {
        File file = Paths.get(cachingDir + key).toFile();
        StringJoiner out = new StringJoiner(System.lineSeparator());
        String rsl = null;
        if (getCache().containsKey(key)) {
            rsl = get(key);
        } else {
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                in.lines().forEach(out::add);
                rsl = out.toString();
                put(key, rsl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }
}
