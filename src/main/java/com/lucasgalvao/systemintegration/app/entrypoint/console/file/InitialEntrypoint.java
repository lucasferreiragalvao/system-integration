package com.lucasgalvao.systemintegration.app.entrypoint.console.file;

import com.lucasgalvao.systemintegration.domain.readfile.interactor.ReadFileInteractor;

import java.util.Scanner;

public class InitialEntrypoint {

    private final ReadFileInteractor readFileInteractor;

    public InitialEntrypoint(ReadFileInteractor readFileInteractor){
        this.readFileInteractor = readFileInteractor;
    }
    public void initialEntrypoint(){
        try {
            String file = this.requestFile();
            System.out.println(file);
            this.readFileInteractor.execute(file);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String requestFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite ou cole o caminho completo do arquivo que deseja normalizar\n");
        return scanner.next();
    }
}
