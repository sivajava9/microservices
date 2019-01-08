package it.sella.sample.microservice.image.samplemicroserviceimage;

import java.util.List;

/**
 * Created by gbs04154 on 07-01-2019.
 */
public class ImageList {

    private List<Image> images;
    private String port;

    public ImageList(final List<Image> images, final String port) {
        this.images = images;
        this.port = port;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(final List<Image> images) {
        this.images = images;
    }

    public String getPort() {
        return port;
    }

    public void setPort(final String port) {
        this.port = port;
    }
}
