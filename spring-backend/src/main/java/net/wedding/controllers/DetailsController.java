package net.wedding.controllers;

import net.wedding.models.details.GoogleMapsDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "details")
@CrossOrigin
public class DetailsController {

    private String googleEmbeddedApiKey;
    private String staybridgeHotelAddress;
    private String weddingLocationAddress;

    public DetailsController(@Value("${googleEmbeddedApiKey}") final String googleEmbeddedApiKey,
                             @Value("${staybridgeHotelAddress}") final String staybridgeHotelAddress,
                             @Value("${weddingLocationAddress}") final String weddingLocationAddress) {
        this.googleEmbeddedApiKey = googleEmbeddedApiKey;
        this.staybridgeHotelAddress = staybridgeHotelAddress;
        this.weddingLocationAddress = weddingLocationAddress;
    }

    @GetMapping(value = "/get-hotel-details")
    public GoogleMapsDetails getHotelDetails() {
        return new GoogleMapsDetails(this.googleEmbeddedApiKey, this.staybridgeHotelAddress);
    }

    @GetMapping(value = "/get-location-details")
    public GoogleMapsDetails getLocationDetails() {
        return new GoogleMapsDetails(this.googleEmbeddedApiKey, this.weddingLocationAddress);
    }

}
