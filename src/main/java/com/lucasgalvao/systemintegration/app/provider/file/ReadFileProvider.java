package com.lucasgalvao.systemintegration.app.provider.file;

import com.lucasgalvao.systemintegration.domain.readfile.gateway.ReadFileGateway;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileProvider implements ReadFileGateway {
    @Override
    public List<String> execute(String dir) throws FileNotFoundException {

        List<String> linesFiles = new ArrayList<>();
        Scanner scn = new Scanner(new FileReader(dir));
        while (scn.hasNextLine()) {
            linesFiles.add(scn.nextLine());
        }
        return linesFiles;
    }
}
