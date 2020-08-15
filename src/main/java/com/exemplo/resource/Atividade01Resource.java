package com.exemplo.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("nomes")
public class Atividade01Resource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNome() {
        return "Sem nomes";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("marcel")
    public String getMarcel() {
        return "Marcel";
    }
}
