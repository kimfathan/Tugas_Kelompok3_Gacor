package com.example.tugas_kelompok3_gacor;

public class Order {
    private String date;
    private String status;
    private String title;
    private String total;
    private String actionText;
    private int statusBgColor;
    private int statusTextColor;
    private int actionBtnColor;

    public Order(String date, String status, String title, String total, String actionText, 
                 int statusBgColor, int statusTextColor, int actionBtnColor) {
        this.date = date;
        this.status = status;
        this.title = title;
        this.total = total;
        this.actionText = actionText;
        this.statusBgColor = statusBgColor;
        this.statusTextColor = statusTextColor;
        this.actionBtnColor = actionBtnColor;
    }

    public String getDate() { return date; }
    public String getStatus() { return status; }
    public String getTitle() { return title; }
    public String getTotal() { return total; }
    public String getActionText() { return actionText; }
    public int getStatusBgColor() { return statusBgColor; }
    public int getStatusTextColor() { return statusTextColor; }
    public int getActionBtnColor() { return actionBtnColor; }
}