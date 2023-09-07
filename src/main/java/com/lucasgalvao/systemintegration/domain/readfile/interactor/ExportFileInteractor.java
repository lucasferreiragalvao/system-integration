package com.lucasgalvao.systemintegration.domain.readfile.interactor;

import java.io.IOException;

public interface ExportFileInteractor {
    String execute(String data) throws IOException;
}
