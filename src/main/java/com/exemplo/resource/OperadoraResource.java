package com.exemplo.resource;

import com.exemplo.modelo.Operadora;
import com.exemplo.modelo.OperadoraService;
import com.exemplo.modelo.Telefone;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("operadoras")
public class OperadoraResource {

    @Inject
    private OperadoraService service;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(200).entity(service.findAll()).build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") int id) {
        Operadora retorno = service.find(id);
        if (retorno != null) {
            return Response.status(200).entity(retorno).build();
        }
        return Response.status(404).entity("Não encontrado o telefone com o id: " + id).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        boolean retorno = service.delete(id);
        if (retorno) {
            return Response.status(204).build();
        }
        return Response.status(404).entity("Não encontrado o telefone com o id: " + id).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Operadora operadora) {
        Operadora retorno = service.update(id, operadora);
        if (retorno != null) {
            return Response.status(200).entity(retorno).build();
        }
        return Response.status(404).entity("Não encontrado o telefone com o id: " + id).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response create(Operadora operadora) {
        return Response.status(201).entity(service.create(operadora)).build();
    }
}
