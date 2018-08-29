package com.example.android.lookforabook;

import android.graphics.Bitmap;

public class Book {
    String title;
    String authors;
    String publishedDate;
    String description;
    String infoLink;
    int pageCount;
    Bitmap image;

    public String getInfoLink() {
        return infoLink;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getImageBitmap() {
        return image;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Book(String title, String authors, String publishedDate, String description, Bitmap image, int pageCount, String infoLink) {

        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.image = image;
        this.pageCount = pageCount;
        this.infoLink = infoLink;
    }
}
