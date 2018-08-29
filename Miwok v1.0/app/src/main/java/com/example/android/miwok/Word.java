package com.example.android.miwok;

public class Word {
    private String DefaultTranslation, MiwokTranslation;
    private int ImageResourceId = -1;
    private int AudioResourceId = 0;


    public Word(String DefaultTranslation, String MiwokTranslation) {

        this.DefaultTranslation = DefaultTranslation;
        this.MiwokTranslation = MiwokTranslation;
    }

    public Word(String DefaultTranslation, String MiwokTranslation, int ImageResourceId) {

        this.DefaultTranslation = DefaultTranslation;
        this.MiwokTranslation = MiwokTranslation;
        this.ImageResourceId = ImageResourceId;
    }

    public Word(String DefaultTranslation, String MiwokTranslation,int ImageResourceId, int AudioResourceId) {

        this.DefaultTranslation = DefaultTranslation;
        this.MiwokTranslation = MiwokTranslation;
        this.ImageResourceId = ImageResourceId;
        this.AudioResourceId = AudioResourceId;
    }

    public String getDefaultTranslation() {
        return DefaultTranslation;
    }

    public String getMiwokTranslation() {
        return MiwokTranslation;
    }

    public int getImageResourceId() {
        return ImageResourceId;
    }

    public int getAudioResourceId() {
        return AudioResourceId;
    }
}
