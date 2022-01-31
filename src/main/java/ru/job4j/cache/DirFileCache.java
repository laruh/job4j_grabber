package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Программа должна считывать текстовые файлы из системы и выдавать текст при запросе имени файла.
 * Если в кеше файла нет. Кеш должен загрузить себе данные.
 * @author Sharon Alina
 * @version 1.1
 */
public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод предназначен для загрузки содержимого файла в кэш.
     * @param key ключ, по которому кэш должен вернуть содержимое файла.
     */
    @Override
    protected String load(String key) {
        String rsl = null;
        try {
            rsl = Files.readString(Paths.get(cachingDir, key));
            put(key, rsl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
