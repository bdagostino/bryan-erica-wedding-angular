package net.wedding.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {


    @GetMapping(path = {"/", "/stories/our-story", "/details/accommodations", "/details/location", "/rsvp/search", "/rsvp/modify"})
    public String home() {
        return "forward:/index.html";
    }


}
