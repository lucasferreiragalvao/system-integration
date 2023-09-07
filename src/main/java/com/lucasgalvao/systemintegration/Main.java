package com.lucasgalvao.systemintegration;

import com.lucasgalvao.systemintegration.app.entrypoint.console.file.InitialEntrypoint;

import java.io.IOException;
public class Main {

    public static void main(String[] args) throws IOException {

        InitialEntrypoint initialEntrypoint = new InitialEntrypoint();
        initialEntrypoint.initialEntrypoint();
    }
}