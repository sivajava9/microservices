package it.sella.sample.microservice.gallery;

import java.util.List;

/**
 * Created by gbs04154 on 03-01-2019.
 */
public class Gallery {

    private Integer id;

    private Object images;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(final Object images) {
        this.images = images;
    }
}
