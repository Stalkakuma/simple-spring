package lt.vidunas.simple_spring.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodbyeController {

    @GetMapping("/goodbye")
    public String goodbye() {
        return "Goodbye!";
    }
}
