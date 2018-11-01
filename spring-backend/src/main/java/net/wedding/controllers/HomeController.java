package net.wedding.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@CrossOrigin
public class HomeController {

    private static final LocalDate WEDDING_DAY = LocalDate.of(2019, 9, 20);

    @GetMapping(path = "/getRemainingDays", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getRemainingDays() throws Exception {
        final long dateDifference = DAYS.between(LocalDate.now(), WEDDING_DAY);
        return Long.toString(dateDifference > 0 ? dateDifference : 0);
    }

    @GetMapping(path = "/getImage1", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    InputStreamResource getImage1() throws Exception {
        return new InputStreamResource(ViewController.class.getResourceAsStream("/images/IMG_7329.jpeg"));
    }

    @GetMapping(path = "/getImage2", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    InputStreamResource getImage2() throws Exception {
        return new InputStreamResource(ViewController.class.getResourceAsStream("/images/P1010144.JPG"));
    }

    @GetMapping(path = "/getImage3", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    InputStreamResource getImage3() throws Exception {
        return new InputStreamResource(ViewController.class.getResourceAsStream("/images/P1000238.JPG"));
    }
}
