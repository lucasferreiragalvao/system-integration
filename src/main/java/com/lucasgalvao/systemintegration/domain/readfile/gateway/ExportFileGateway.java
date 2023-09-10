package com.lucasgalvao.systemintegration.domain.readfile.gateway;

import java.io.IOException;

public interface ExportFileGateway {
    String execute(String data) throws IOException;
}
