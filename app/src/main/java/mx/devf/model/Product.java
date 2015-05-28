package mx.devf.model;

import java.util.ArrayList;

/**
 * Created by hugo on 5/27/15.
 */
public class Product {
    private String productName;
    private String productId;
    private ArrayList<Precio> precios;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ArrayList<Precio> getPrecios() {
        return precios;
    }

    public void setPrecios(ArrayList<Precio> precios) {
        this.precios = precios;
    }
}
