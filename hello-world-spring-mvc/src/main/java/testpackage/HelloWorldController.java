package testpackagey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //this annotation marks this class as a controller 
public class HelloWorldController {

    @GetMapping("/")   //maps the root path ("/") to the helloWorldmethod.
    public String helloWorld() {
        return "Hello World!";
    }
}
