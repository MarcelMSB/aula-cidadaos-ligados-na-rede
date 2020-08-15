package com.exemplo.resource;

import com.exemplo.modelo.Telefone;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("atividade034")
public class Atividade04Resource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("soma")
    public Response getSoma(@QueryParam("n1") int n1, @QueryParam("n2") int n2) {
        int resultado = n1 + n2;
        if (resultado >= 100) {
            return Response.status(422).entity("Ultrapassou o limite de 100").build();
        }
        return Response.status(200).entity("Resultado: " + resultado).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("multiplica/{n1}")
    public Response getMultiplicacao(@PathParam("n1") int n1) {
        int resultado = n1 * 10;
        if (resultado >= 100) {
            return Response.status(400).entity("Ultrapassou o limite de 100").build();
        }
        return Response.status(200).entity("Resultado: " + resultado).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("telefones")
    public Response getDemonstra(Telefone telefone) {
        telefone.setId(1);
        return Response.status(200).entity(telefone).build();
    }
}
