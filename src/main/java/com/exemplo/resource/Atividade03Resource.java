package com.exemplo.resource;

import java.util.Map;
import java.util.StringJoiner;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("atividade03")
public class Atividade03Resource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("soma")
    public String getSoma(@QueryParam("n1") int n1, @QueryParam("n2") int n2) {
        int resultado = n1 + n2;
        return "Resultado: " + resultado;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("multiplica/{n1}")
    public String getMultiplica(@PathParam("n1") int n1) {
        int resultado = n1 * 10;
        return "Resultado: " + resultado;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("demonstra")
    public String getDemonstra(Map<String, String> campos) {
        StringJoiner joiner = new StringJoiner(", ");
        campos.forEach((key, v) -> joiner.add("nome: "+ key +", valor: " + v));
        return joiner.toString();
    }
}
