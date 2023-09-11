package com.lucasgalvao.systemintegration.app.provider.file;

import com.lucasgalvao.systemintegration.domain.readfile.gateway.ExportFileGateway;
import com.lucasgalvao.systemintegration.util.Formatter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class ExportFileProvider implements ExportFileGateway {
    @Override
    public String execute(String data) throws IOException {
        String nameFile = Formatter.dateformat("yyyyMMddHHmms", new Date());
        Path path = Paths.get(this.createDirNormalized(), String.format("%s.json", nameFile));
        Files.write(path, data.getBytes(), StandardOpenOption.CREATE);
        return nameFile;
    }

    private String createDirNormalized(){
        File dir = new File("src/main/resources/files/normalized");

        if(!dir.exists()){
            dir.mkdir();
        }

        return dir.getPath();
    }

}
