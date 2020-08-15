package com.exemplo.resource;

import com.exemplo.util.BancoResultado;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("calculos")
public class CalculoResource {

    @Inject
    private BancoResultado bancoResultado;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("soma")
    public Response soma(@QueryParam("v1") int v1, @QueryParam("v2") int v2) {
        int resultado = v1 + v2;
        bancoResultado.addResultado(resultado);
        return Response.status(200).entity(resultado).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("resultados")
    public Response resultado() {
        return Response.status(200).entity(bancoResultado.getBanco()).build();
    }
}
