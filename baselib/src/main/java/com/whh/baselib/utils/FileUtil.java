package com.whh.baselib.utils;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static void createFile(String path) {
        createFile(path, null);
    }

    public static void createFile(String path, String fileName) {
        File file = null;
        if (path == null) {
            new Exception("path no null");
        }
        if (fileName == null) {
            file = new File(path);
        } else {
            file = new File(path, fileName);
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
