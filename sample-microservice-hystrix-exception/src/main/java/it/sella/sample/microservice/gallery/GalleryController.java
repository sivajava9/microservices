package it.sella.sample.microservice.gallery;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeoutException;

/**
 * Created by gbs04154 on 03-01-2019.
 */
@RestController
@RequestMapping("/")
@DefaultProperties(defaultFallback = "defaultGallery")
public class GalleryController {

    private Logger LOGGER = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    private RestTemplate restTemplate;

    //raiseHystrixExceptions = HystrixException.RUNTIME_EXCEPTION
    @HystrixCommand(commandKey = "GetGallery",groupKey = "GalleryControllerGroup", fallbackMethod = "galleryFallBack", ignoreExceptions = TimeoutException.class)
    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);
        // get list of available images
        ResponseEntity<GalleryImageList> response = restTemplate.getForEntity("http://image-service/images/", GalleryImageList.class);
        GalleryImageList images = response.getBody();
        gallery.setImages(images);
        return gallery;
    }

    // any access modifier with same method
    private Gallery galleryFallBack(final int galleryId, Throwable hystrixCommand){
        LOGGER.error("Fallback cause", hystrixCommand);
        Gallery fallbackGallery = new Gallery();
        fallbackGallery.setId(galleryId);
        GalleryImageList galleryImageList = new GalleryImageList();
        fallbackGallery.setImages(galleryImageList);
        return fallbackGallery;
    }

}
