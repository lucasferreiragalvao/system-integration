package com.lucasgalvao.systemintegration.app.entrypoint.mock;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamMock {
    public static OutputStream createOutputStream(){
        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                return;
            }

            @Override
            public void close() {
            }
        };
    }
}
