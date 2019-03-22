package it.sella.sample.microservice.gallery;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
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
public class GalleryController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(commandKey = "GetGallery",groupKey = "GalleryControllerGroup", fallbackMethod = "galleryFallBack", threadPoolProperties =
        {@HystrixProperty(name = "coreSize", value = "1"), @HystrixProperty(name = "maxQueueSize", value = "1"), @HystrixProperty(name = "queueSizeRejectionThreshold", value = "1")})
    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final String id) {
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
    private Gallery galleryFallBack(final String galleryId, Throwable hystrixCommand){
        if(! (hystrixCommand instanceof com.netflix.hystrix.exception.HystrixTimeoutException)){
            System.out.println(hystrixCommand.getMessage());
        }else{
            System.out.println("Timeout Fallback");
            System.out.println(hystrixCommand.getClass());
        }
        Gallery fallbackGallery = new Gallery();
        fallbackGallery.setId(galleryId);
        GalleryImageList galleryImageList = new GalleryImageList();
        fallbackGallery.setImages(galleryImageList);
        return fallbackGallery;
    }

}
