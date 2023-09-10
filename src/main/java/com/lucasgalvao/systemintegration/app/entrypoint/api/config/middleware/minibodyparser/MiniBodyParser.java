package com.lucasgalvao.systemintegration.app.entrypoint.api.config.middleware.minibodyparser;

import com.lucasgalvao.systemintegration.app.entrypoint.api.config.middleware.minibodyparser.props.Props;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniBodyParser {

    public static Map<String, Props> transformBodyParser(HttpExchange exchange) throws IOException {
        String request = readBufferRequest(exchange);
        return getPropsRequest(splitRequestBody(request));
    }

    private static Map<String, Props> getPropsRequest(String[] requestBody) {


        Map<String,Props> props = new HashMap<>();

        for(String rb : requestBody){
            Pattern patternNamePropRequest = Pattern.compile("name=\"([^\"]+)\"");
            Matcher matcherNamePropRequest = patternNamePropRequest.matcher(rb);

            Props propsField = new Props();

            if(matcherNamePropRequest.find()){
                String[] content = rb.split("\n",5);
                Pattern patternContainsContentType = Pattern.compile("Content-Type: [^\\n]+");
                Matcher matcherContainsContentType = patternContainsContentType.matcher(rb);

                if(matcherContainsContentType.find()) {
                    Pattern patternGetFilename = Pattern.compile("filename=\"([^\"]+)\"");
                    Matcher matcherGetFileName = patternGetFilename.matcher(rb);

                    if(matcherGetFileName.find()){
                        propsField.setFilename(matcherGetFileName.group(1));
                    }
                    propsField.setValue(content[4].trim());
                }else {
                    propsField.setValue(content[3].trim());
                }
                props.put(matcherNamePropRequest.group(1), propsField);
            }
        }
        return props;
    }

    private static String[] splitRequestBody(String request){
        String regex = "--+";
        return request.trim().split(regex);
    }

    private static String readBufferRequest(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();

        byte[] buffer = new byte[1024];
        int bytesRead;
        StringBuilder requestBodyContent = new StringBuilder();

        while ((bytesRead = requestBody.read(buffer)) != -1) {
            requestBodyContent.append(new String(buffer, 0, bytesRead));
        }
        return requestBodyContent.toString();
    }

}
