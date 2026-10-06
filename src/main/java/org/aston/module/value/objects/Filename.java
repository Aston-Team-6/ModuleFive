package org.aston.module.value.objects;

import java.io.IOException;

public class Filename {
    private String filename;

    public Filename(String filename, String fileExtension) throws IOException {
        if(isValidFileFormat(filename, fileExtension))
            this.filename = filename;
        else throw new IOException("Неверный формат файла!");

    }

    public boolean isValidFileFormat(String filename, String fileExtension) throws IOException {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex != -1) {
            String extension = filename.substring(dotIndex + 1).toUpperCase();
            return extension.equals(fileExtension.toUpperCase());
        }
        throw new IOException("Неверный формат файла!");
    }

    public String getValue(){
        return filename;
    }
}

