package com.lucasgalvao.systemintegration.app.entrypoint.api.config.minibodyparser;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniBodyParser {

    public static Map<String,String> transformBodyParser(HttpExchange exchange) throws IOException {
        String request = readBufferRequest(exchange);
        return getPropsRequest(splitRequestBody(request));
    }

    private static Map<String, String> getPropsRequest(String[] requestBody) {
        Map<String,String> props = new HashMap<>();

        for(String rb : requestBody){
            Pattern patternNamePropRequest = Pattern.compile("name=\"([^\"]+)\"");
            Matcher matcherNamePropRequest = patternNamePropRequest.matcher(rb);

            if(matcherNamePropRequest.find()){
                String[] content = rb.split("\n",5);
                Pattern patternContainsContentType = Pattern.compile("Content-Type: [^\\n]+");
                Matcher matcherContainsContentType = patternContainsContentType.matcher(rb);
                if(matcherContainsContentType.find()) {
                    props.put(matcherNamePropRequest.group(1), content[4].trim());
                }else {
                    props.put(matcherNamePropRequest.group(1), content[3].trim());
                }
            }
        }
        return props;
    }

    private static String[] splitRequestBody(String request){
        return request.trim().split("----------------------------");
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
