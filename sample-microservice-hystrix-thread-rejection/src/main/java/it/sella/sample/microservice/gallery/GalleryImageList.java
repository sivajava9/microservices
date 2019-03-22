package it.sella.sample.microservice.gallery;

import java.util.List;

/**
 * Created by gbs04154 on 07-01-2019.
 */
public class GalleryImageList {

    private List<GalleryImage> images;
    private String port;

    public GalleryImageList(){

    }

    public List<GalleryImage> getImages() {
        return images;
    }

    public void setImages(final List<GalleryImage> images) {
        this.images = images;
    }

    public String getPort() {
        return port;
    }

    public void setPort(final String port) {
        this.port = port;
    }
}
