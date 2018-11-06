package net.wedding.models.details;

public class GoogleMapsDetails {

    private String googleApiKey;
    private String searchAddress;

    public GoogleMapsDetails(final String googleApiKey, final String searchAddress) {
        this.googleApiKey = googleApiKey;
        this.searchAddress = searchAddress;
    }

    public String getGoogleApiKey() {
        return googleApiKey;
    }

    public void setGoogleApiKey(String googleApiKey) {
        this.googleApiKey = googleApiKey;
    }

    public String getSearchAddress() {
        return searchAddress;
    }

    public void setSearchAddress(String searchAddress) {
        this.searchAddress = searchAddress;
    }
}
