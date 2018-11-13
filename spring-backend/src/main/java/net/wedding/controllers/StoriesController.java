package net.wedding.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/stories")
public class StoriesController {

    private static final Logger logger = LogManager.getLogger(StoriesController.class);

    private static final String FORWARD_INDEX = "forward:/index.html";

    @GetMapping(value = "/our-story")
    public String ourStory() {
        logger.debug("Our Story Page Accessed");
        return FORWARD_INDEX;
    }

    @GetMapping(value = "/bridesmaids")
    public String bridesmaids() {
        logger.debug("Bridesmaids Page Accessed");
        return FORWARD_INDEX;
    }

    @GetMapping(value = "/groomsmen")
    public String groomsmen() {
        logger.debug("Groomsmen Page Accessed");
        return FORWARD_INDEX;
    }

    @GetMapping(value = "/load-maid-honor-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getMaidOfHonor() {
        return buildImageResponse("images/stories/bridesmaids/brooke.jpg");
    }

    @GetMapping(value = "/load-bridesmaid-1-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getBridesmaid1() {
        return buildImageResponse("images/stories/bridesmaids/colleen.jpg");
    }

    @GetMapping(value = "/load-bridesmaid-2-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getBridesmaid2() {
        return buildImageResponse("images/stories/bridesmaids/ashley.jpg");
    }

    @GetMapping(value = "/load-bridesmaid-3-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getBridesmaid3() {
        return buildImageResponse("images/stories/bridesmaids/amanda.jpg");
    }

    private ResponseEntity<InputStreamResource> buildImageResponse(final String imagePath) {
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(new ClassPathResource(imagePath).getInputStream()));
        } catch (IOException e) {
            logger.error(e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }
}
