package com.example.tugas_kelompok3_gacor;

public class CartItem {
    private String name;
    private String brand;
    private String price;
    private int quantity;

    public CartItem(String name, String brand, String price, int quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}