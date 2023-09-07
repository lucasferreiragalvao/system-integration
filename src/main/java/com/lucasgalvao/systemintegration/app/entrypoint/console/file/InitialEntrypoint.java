package com.lucasgalvao.systemintegration.app.entrypoint.console.file;

import java.util.Scanner;

public class InitialEntrypoint {
    public void initialEntrypoint(){
        String file = this.requestFile();
        System.out.println(file);
    }
    public String requestFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite ou cole o caminho completo do arquivo que deseja normalizar\n");
        return scanner.next();
    }
}
