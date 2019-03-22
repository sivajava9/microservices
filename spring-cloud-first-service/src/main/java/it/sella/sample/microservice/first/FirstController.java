package it.sella.sample.microservice.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gbs04154 on 28-02-2019.
 */
@RestController
public class FirstController {

    @GetMapping("/")
    public String getFirstService(){
        return "First Service called with GET Method";
    }

}


