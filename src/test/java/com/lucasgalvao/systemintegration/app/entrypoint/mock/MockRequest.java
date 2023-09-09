package com.lucasgalvao.systemintegration.app.entrypoint.mock;

public class MockRequest {
    public static String createMockRequest(){
        return "----------------------------672693325227411239335170\n"+
                "Content-Disposition: form-data; name=\"file\"; filename=\"data_1.txt\"\n"+
                "Content-Type: text/plain\n"+
                "\n"+
                "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
    }

    public static String creteMockRequestWithOthersFields (){
        return "----------------------------672693325227411239335170\n"+
                "Content-Disposition: form-data; name=\"file\"; filename=\"data_1.txt\"\n"+
                "\n"+
                "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
    }
}
