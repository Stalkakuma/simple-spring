package lt.vidunas.simple_spring.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/greetings")
    public String greetings() {
        return "Hello World";
    }
}
