package shivani.food_odering.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<String> homecontroller() {
        return new ResponseEntity<>("Wellcome To Food Delivery App", HttpStatus.OK);
    }
}
