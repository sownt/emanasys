package com.vosxvo.java.services.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String CONFIGURATION_FILE = System.getProperty("user.home") + "/.employee-management-system/setup.conf";
    public static Properties load() throws IOException {
        File file = new File(CONFIGURATION_FILE);

        if (!file.exists())
            return null;

        FileReader reader = new FileReader(file);
        Properties props = null;
        if (reader != null) {
            props = new Properties();
            props.load(reader);
            reader.close();
        } else
            return null;

        return props;
    }
    public static boolean save(Properties props) throws IOException {
        File file = new File(CONFIGURATION_FILE);

        if (!file.exists())
            file.getParentFile().mkdir();
        FileWriter writer = new FileWriter(file);

        if (writer != null) {
            props.store(writer, "Employee Management System Configuration");
            writer.close();
        } else
            return false;

        return true;
    }
}