package it.sella.sample.microservice.gallery;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private ImageServiceProxy imageServiceProxy;


@HystrixCommand(commandKey = "GetGallery",groupKey = "GalleryControllerGroup", fallbackMethod = "getGalleryFeign", defaultFallback = "defaultFallbackGallery")
    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {
    LOGGER.info("Primary server Rest Template call");
    // create gallery object
    Gallery gallery = new Gallery();
    gallery.setId(id);
    // get list of available images
    ResponseEntity<GalleryImageList> response = restTemplate.getForEntity("http://image-service/images/", GalleryImageList.class);
    GalleryImageList images = response.getBody();
    gallery.setImages(images);
    return gallery;
    }

    @HystrixCommand(fallbackMethod = "galleryFallBack")
    public Gallery getGalleryFeign(final int id, Throwable hystrixCommand) {
        LOGGER.info("Secondary server Feign client call");
        LOGGER.error("Secondary server Fallback cause", hystrixCommand);
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);
        GalleryImageList images = imageServiceProxy.getImages();
        gallery.setImages(images);
        return gallery;
    }

    // any access modifier with same method
    private Gallery galleryFallBack(final int galleryId, Throwable hystrixCommand){
        LOGGER.info("Fallback call");
        LOGGER.error("Fallback cause", hystrixCommand);
        Gallery fallbackGallery = new Gallery();
        fallbackGallery.setId(galleryId);
        GalleryImageList galleryImageList = new GalleryImageList();
        fallbackGallery.setImages(galleryImageList);
        return fallbackGallery;
    }

    private Gallery defaultFallbackGallery(Throwable hystrixCommand){
        LOGGER.debug(" Method level Default Fallback call");
        LOGGER.error("Method level Default Fallback cause", hystrixCommand);
        Gallery fallbackGallery = new Gallery();
        fallbackGallery.setId(1);
        List<GalleryImage> images = Arrays.asList(
                    new GalleryImage(11, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                    new GalleryImage(22, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
                    new GalleryImage(33, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));
        GalleryImageList galleryImageList = new GalleryImageList();
        galleryImageList.setImages(images);
        fallbackGallery.setImages(galleryImageList);
        return fallbackGallery;
    }

    private Gallery defaultGallery(Throwable hystrixCommand){
        LOGGER.info(" Class level Default Fallback call");
        LOGGER.error("Class level Default Fallback cause", hystrixCommand);
        Gallery fallbackGallery = new Gallery();
        fallbackGallery.setId(0);
        GalleryImageList galleryImageList = new GalleryImageList();
        fallbackGallery.setImages(galleryImageList);
        return fallbackGallery;
    }

}
