package com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasgalvao.systemintegration.util.Formatter;
import com.lucasgalvao.systemintegration.domain.readfile.entity.product.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

    @JsonProperty("product_id")
    private int productId;

    @JsonProperty("value")
    private String amountProduct;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(String amountProduct) {
        this.amountProduct = amountProduct;
    }

    public static List<ProductResponse> orderEntityToResponse(List<ProductEntity> listProductsEntity) {

        List<ProductResponse> listProductsResponse = new ArrayList<>();
        for(ProductEntity productEntity : listProductsEntity) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductId(productEntity.getProductId());
            productResponse.setAmountProduct(Formatter.decimalFormat("#.00",productEntity.getAmountProduct(),true));
            listProductsResponse.add(productResponse);
        }

        return listProductsResponse;
    }
}
