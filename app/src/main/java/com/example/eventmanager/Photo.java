package com.example.eventmanager;

public class Photo {
    private String imageView;

    public Photo() {

    }
    public Photo(String imageView) {
        this.imageView = imageView;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "imageView='" + imageView + '\'' +
                '}';
    }
}
