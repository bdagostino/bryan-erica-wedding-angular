package net.wedding.models;

import java.util.ArrayList;
import java.util.List;

public class UriResponse {

    private String uri;
    private List<ErrorType> errors;

    public UriResponse(){
    }

    public UriResponse(final String uri){
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<ErrorType> getErrors() {
        if(this.errors==null){
            this.errors = new ArrayList<>();
        }
        return errors;
    }
}
