package com.jobtracker.user_service.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JsonTemplateReader {

    public static String readAndReplace(String path, Map<String, String> values) throws Exception {

        String json = new String(Files.readAllBytes(Paths.get(path)));

        for (Map.Entry<String, String> entry : values.entrySet()) {
            json = json.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }

        return json;
    }

}
