package it.sella.sample.microservice.gallery;

/**
 * Created by gbs04154 on 03-01-2019.
 */
public class GalleryImage {

    private Integer id;
    private String title;
    private String url;

    public GalleryImage(){

    }

    public GalleryImage(Integer id, String title, String url){
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

}
