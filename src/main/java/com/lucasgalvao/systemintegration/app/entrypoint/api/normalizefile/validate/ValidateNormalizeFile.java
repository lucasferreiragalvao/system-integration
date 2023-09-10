package com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.validate;

import com.lucasgalvao.systemintegration.app.entrypoint.api.config.minibodyparser.props.Props;
import com.sun.net.httpserver.HttpExchange;
import java.util.Map;

public class ValidateNormalizeFile {
    public static void validateNormalizeField(HttpExchange exchange, Map<String, Props> propsRequest) throws ErrorValidateNormalizeFile {
        validateFieldFile(exchange, propsRequest);
    }

    private static void validateFieldFile(HttpExchange exchange, Map<String, Props> propsRequest) throws ErrorValidateNormalizeFile {

            if (propsRequest.get("file") == null) throw new ErrorValidateNormalizeFile("Campo file deve ser enviado");

            if (!propsRequest.get("file").getFilename().contains(".txt")) throw new ErrorValidateNormalizeFile("O campo file deve ser no formato txt");

    }
}
