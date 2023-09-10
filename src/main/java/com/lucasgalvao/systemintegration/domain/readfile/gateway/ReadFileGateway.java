package com.lucasgalvao.systemintegration.domain.readfile.gateway;

import java.io.FileNotFoundException;
import java.util.List;

public interface ReadFileGateway {
    List<String> execute(String dir) throws FileNotFoundException;
}
