package com.niudi.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Zhangyz
 * @description
 * @date 2019/9/24
 */
public class FileUtil {
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(filecontent);
            return new String(filecontent, encoding);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
