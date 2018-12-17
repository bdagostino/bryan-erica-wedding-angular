package net.wedding.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/admin")
public class AdminViewController {
    private static final Logger logger = LogManager.getLogger(AdminViewController.class);

    private static final String FORWARD_INDEX = "forward:/index.html";

    @GetMapping(value = "/food")
    public String food() {
        logger.debug("Food Admin Page Accessed");
        return FORWARD_INDEX;
    }

    @GetMapping(value = "/guest")
    public String guest() {
        logger.debug("Guest Admin Page Accessed");
        return FORWARD_INDEX;
    }

    @GetMapping(value = "/invitation")
    public String invitation() {
        logger.debug("Invitation Admin Page Accessed");
        return FORWARD_INDEX;
    }
}
