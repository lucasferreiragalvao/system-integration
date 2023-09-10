package com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.integration;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.NormalizeFileEndpoint;
import com.lucasgalvao.systemintegration.domain.readfile.usecase.ProcessContentFileBufferUseCase;
import com.sun.net.httpserver.HttpServer;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@ExtendWith(MockitoExtension.class)
public class NormalizeFileEndpointTestIntegration {

    private final String BASE_URL = "http://localhost:8080/api/normalize-file";
    private final Gson gson = new Gson();
    private static HttpServer server;
    private static final int PORT = 8080;

    private String valueFile = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";

    @BeforeAll
    public static void setUp() throws IOException {
        ProcessContentFileBufferUseCase readFileUseCase = new ProcessContentFileBufferUseCase();
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/api/normalize-file", new NormalizeFileEndpoint(readFileUseCase));
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();
    }

    @AfterAll
    public static void tearDown() {
        server.stop(0);
    }


    @Test
    @DisplayName("should return status 405 when method not allowed")
    public void shouldReturnStatus405WhenMethodNotAllowed() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getCode();
        Assertions.assertEquals(405, statusCode);
    }

    @Test
    @DisplayName("should return status 200 when the file field sent is valid")
    public void shouldReturnStatus200WhenTheFileFieldSentIsValid() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL);

        byte[] fileContent = valueFile.getBytes("UTF-8");

        HttpEntity entity = MultipartEntityBuilder.create()
                .addBinaryBody("file", fileContent, ContentType.DEFAULT_TEXT, "filename.txt")
                .build();

        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        JsonArray jsonResponse = gson.fromJson(EntityUtils.toString(response.getEntity()), JsonArray.class);

        JsonArray jsonArrayOrders = jsonResponse.get(0).getAsJsonObject().getAsJsonArray("orders");
        JsonArray jsonArrayProducts = jsonArrayOrders.get(0).getAsJsonObject().getAsJsonArray("products");

        Assertions.assertEquals(200, response.getCode());
        Assertions.assertEquals(1, jsonResponse.size());
        Assertions.assertEquals(jsonResponse.get(0).getAsJsonObject().get("user_id").getAsInt(), 70);
        Assertions.assertEquals(jsonResponse.get(0).getAsJsonObject().get("name").getAsString(), "Palmer Prosacco");
        Assertions.assertEquals(jsonArrayOrders.get(0).getAsJsonObject().get("order_id").getAsInt(),753);
        Assertions.assertEquals(jsonArrayOrders.get(0).getAsJsonObject().get("total").getAsString(),"1836.74");
        Assertions.assertEquals(jsonArrayOrders.get(0).getAsJsonObject().get("date").getAsString(),"2021-03-08");

        Assertions.assertEquals(jsonArrayProducts.get(0).getAsJsonObject().get("product_id").getAsString(),"3");

        Assertions.assertEquals(jsonArrayProducts.get(0).getAsJsonObject().get("value").getAsString(),"1836.74");
    }

    @Test
    @DisplayName("should return status 400 when the file field not sent")
    public void shouldReturnStatus400WhenTheFileFieldNotSent() throws ParseException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL);

        byte[] fileContent = valueFile.getBytes("UTF-8");

        HttpEntity entity = MultipartEntityBuilder.create()
                .addBinaryBody("file1", fileContent, ContentType.DEFAULT_TEXT, "filename.txt")
                .build();


        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        JsonObject jsonResponse = gson.fromJson(EntityUtils.toString(response.getEntity()), JsonObject.class);
        int statusCode = response.getCode();
        Assertions.assertEquals(400, statusCode);
        Assertions.assertEquals(jsonResponse.getAsJsonObject().get("message").getAsString(), "Campo file deve ser enviado");
    }

    @Test
    @DisplayName("should return status 400 when the file sent is not txt format")
    public void shouldReturnStatus400WhenTheFileSentIsNotTxtFormat() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL);

        byte[] fileContent = valueFile.getBytes("UTF-8");

        HttpEntity entity = MultipartEntityBuilder.create()
                .addBinaryBody("file", fileContent, ContentType.DEFAULT_TEXT, "filename.csv")
                .build();


        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        JsonObject jsonResponse = gson.fromJson(EntityUtils.toString(response.getEntity()), JsonObject.class);
        int statusCode = response.getCode();
        Assertions.assertEquals(400, statusCode);
        Assertions.assertEquals(jsonResponse.getAsJsonObject().get("message").getAsString(), "O campo file deve ser no formato txt");
    }

}
