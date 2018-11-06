package net.wedding.controllers;

import net.wedding.models.details.GoogleMapsDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class DetailsControllerTest {

    private static final String GOOGLE_API_KEY = "GoOgLeApIkEy";
    private static final String HOTEL_ADDRESS = "1234 Hotel Lane";
    private static final String WEDDING_ADDRESS = "1126 Wedding Rd";

    private DetailsController detailsController;

    @Before
    public void setup() {
        this.detailsController = new DetailsController(GOOGLE_API_KEY, HOTEL_ADDRESS, WEDDING_ADDRESS);
    }

    @Test
    public void testGetHotelDetails() {
        final GoogleMapsDetails hotelDetails = this.detailsController.getHotelDetails();
        assertThat(hotelDetails.getGoogleApiKey()).isEqualTo(GOOGLE_API_KEY);
        assertThat(hotelDetails.getSearchAddress()).isEqualTo(HOTEL_ADDRESS);
    }

    @Test
    public void testGetLocationDetails(){
        final GoogleMapsDetails locationDetails = this.detailsController.getLocationDetails();
        assertThat(locationDetails.getGoogleApiKey()).isEqualTo(GOOGLE_API_KEY);
        assertThat(locationDetails.getSearchAddress()).isEqualTo(WEDDING_ADDRESS);
    }
}
