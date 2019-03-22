package it.sella.sample.microservice.second;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gbs04154 on 28-02-2019.
 */
@RestController
public class SecondController {

    @PostMapping("/")
    public String postSecondService(){
        return "Second Service called with POST Method";
    }

}
