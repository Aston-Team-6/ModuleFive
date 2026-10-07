package org.aston.module.domain.values.objects;

import java.io.IOException;

public class JsonFilename {
    private String filename;

    public JsonFilename(String filename) throws IOException {
        if (isValidFileFormat(filename)) {
            this.filename = filename;
        } else {
            throw new IOException("Неверный формат файла!");
        }
    }

    public boolean isValidFileFormat(String filename) throws IOException {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex != -1) {
            String extension = filename.substring(dotIndex + 1).toUpperCase();
            return extension.equals("JSON");
        }
        throw new IOException("Неверный формат файла!");
    }

    public String getValue() {
        return filename;
    }
}

