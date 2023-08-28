package dev.microprofile.starter;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/resource")
public class StarterResource {

    @GET
    public String getRequest() {
        return "Hello, world!";
    }
}
