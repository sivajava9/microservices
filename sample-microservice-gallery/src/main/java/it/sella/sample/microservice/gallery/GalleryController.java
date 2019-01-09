package it.sella.sample.microservice.gallery;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gbs04154 on 03-01-2019.
 */
@RestController
@RequestMapping("/")
public class GalleryController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ImageServiceProxy imageServiceProxy;

    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);

        // get list of available images
//        Map<String, Integer> uriFields = new HashMap<>();
//        uriFields.put("id",1);
        ResponseEntity<GalleryImageList> response = restTemplate.getForEntity("http://localhost:8200/images/", GalleryImageList.class);
        GalleryImageList images = response.getBody();
        gallery.setImages(images);
        return gallery;
    }


    //    @HystrixCommand(fallbackMethod = "galleryFallBack")
    /*@RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);

        // get list of available images
        ResponseEntity<GalleryImageList> response = restTemplate.getForEntity("http://image-service/images/", GalleryImageList.class);
        GalleryImageList images = response.getBody();
        gallery.setImages(images);
        return gallery;
    }*/


    @RequestMapping("/feign/{id}")
    public Gallery getGalleryFeign(@PathVariable final int id) {
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);
        GalleryImageList images = imageServiceProxy.getImages();
        gallery.setImages(images);

        return gallery;
    }

    /*
    public Gallery galleryFallBack(final int galleryId, Throwable hystrixCommand){
        Gallery fallbackGallery = new Gallery();
        fallbackGallery.setId(galleryId);
        return fallbackGallery;
    }
    */

}
