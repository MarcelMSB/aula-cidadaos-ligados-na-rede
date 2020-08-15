package com.exemplo.resource;

import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("atividade02")
public class Atividade02Resource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getQueryParam(@QueryParam("id") int id) {
        return "Marcel: " + id;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getPathParam(@PathParam("id") String id) {
        return "Marcel: " + id;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getBody(Map<String, String> nomes) {
        return "Marcel: " + nomes.toString();
    }
}
