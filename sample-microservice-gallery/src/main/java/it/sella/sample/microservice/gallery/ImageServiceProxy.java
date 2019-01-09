package it.sella.sample.microservice.gallery;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gbs04154 on 09-01-2019.
 */
@FeignClient(name="image-service", url = "localhost:8200")
//@FeignClient(name="image-service")
//@RibbonClient("image-service")
public interface ImageServiceProxy {

    @RequestMapping("/images")
    public GalleryImageList getImages();

}
