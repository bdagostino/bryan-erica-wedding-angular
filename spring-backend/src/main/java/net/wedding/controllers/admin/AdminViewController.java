package net.wedding.controllers.admin;

import net.wedding.models.Food;
import net.wedding.repositories.FoodRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/admin")
public class AdminViewController {
    private static final Logger logger = LogManager.getLogger(AdminViewController.class);

    private static final String FORWARD_INDEX = "forward:/index.html";
    private FoodRepository foodRepository;

    public AdminViewController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping(value = "/food")
    public String food() {
        // logger.debug("Our Story Page Accessed");
        return FORWARD_INDEX;
    }

    @GetMapping(value = "/guest")
    public String guest() {
        // logger.debug("Our Story Page Accessed");
        return FORWARD_INDEX;
    }

    @GetMapping(value = "/invitation")
    public String invitation() {
        // logger.debug("Our Story Page Accessed");
        return FORWARD_INDEX;
    }
}
