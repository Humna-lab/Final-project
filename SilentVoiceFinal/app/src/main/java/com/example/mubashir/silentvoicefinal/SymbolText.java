package com.example.mubashir.silentvoicefinal;

/**
 * Created by mubas on 09/04/2018.
 */

public class SymbolText {

    private String title;
    private int thumbnail;
    private int imageId;

    public SymbolText() {
    }




    public SymbolText(String title, int thumbnail, int imageId) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }
    public int getThumbnail() {
        return thumbnail;
    }
    public int getImageId() {
        return imageId;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
