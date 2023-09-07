package com.lucasgalvao.systemintegration.app.entrypoint.console.file;

import com.lucasgalvao.systemintegration.app.entrypoint.console.file.response.userorder.UserOrderResponse;
import com.lucasgalvao.systemintegration.app.entrypoint.util.Transform;
import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;
import com.lucasgalvao.systemintegration.domain.readfile.interactor.ExportFileInteractor;
import com.lucasgalvao.systemintegration.domain.readfile.interactor.ReadFileInteractor;

import java.util.List;
import java.util.Scanner;

public class InitialEntrypoint {

    private final ReadFileInteractor readFileInteractor;
    private final ExportFileInteractor exportFileInteractor;

    public InitialEntrypoint(ReadFileInteractor readFileInteractor, ExportFileInteractor exportFileInteractor){
        this.readFileInteractor = readFileInteractor;
        this.exportFileInteractor = exportFileInteractor;
    }
    public void initialEntrypoint(){
        try {
            String file = this.requestFile();
            List<UserOrderEntity> userOrderEntityList = this.readFileInteractor.execute(file);
            List<UserOrderResponse> orderResponseList = UserOrderResponse.userOrderEntityToResponse(userOrderEntityList);
            String json = Transform.transformObjToJson(orderResponseList);
            String fileName = this.exportFileInteractor.execute(json);
            System.out.println(String.format("Arquivo normalizado e exportado dentro da pastas resources/normalized com o nome %s.json", fileName));
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
