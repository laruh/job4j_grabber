package ru.job4j.cache;

import java.io.File;
import java.nio.file.Paths;

public class Emulator {

    public static DirFileCache emulateCacheDir(String cachingDir) {
        File fileIn = Paths.get(cachingDir).toFile();
        if (!fileIn.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", fileIn.getAbsoluteFile()));
        }
        if (!fileIn.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", fileIn.getAbsolutePath()));
        }
        return new DirFileCache(cachingDir);
    }

    public static void putCache(DirFileCache dirFileCache, String key, String value) {
        dirFileCache.put(key, value);
    }

    public static String loadCache(DirFileCache dirFileCache, String key) {
        return dirFileCache.get(key);
    }

    public static void main(String[] args) {
        DirFileCache dirFileCache = emulateCacheDir("C:/project/cache/");
        putCache(dirFileCache, "some_cache", "Hello there!");
        System.out.println(loadCache(dirFileCache, "Names.txt"));
        System.out.println(loadCache(dirFileCache, "some_cache"));
        System.out.println(loadCache(dirFileCache, "do_not_exist"));
    }
}
