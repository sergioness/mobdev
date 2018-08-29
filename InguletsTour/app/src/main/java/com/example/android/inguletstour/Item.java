package com.example.android.inguletstour;

public class Item {
    private String header, bodyText;
    private int imageResourceId = -1;

    public Item(String header, String bodyText, int imageResourceId) {
        this.header = header;
        this.bodyText = bodyText;
        this.imageResourceId = imageResourceId;
    }

    public String getHeader() {
        return header;
    }

    public String getBodyText() {
        return bodyText;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
