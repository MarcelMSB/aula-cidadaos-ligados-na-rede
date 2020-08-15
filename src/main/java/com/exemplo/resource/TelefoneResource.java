package com.exemplo.resource;

import com.exemplo.modelo.Telefone;
import com.exemplo.modelo.TelefoneService;
import com.exemplo.util.CurtaMemoria;
import com.exemplo.util.LongaMemoria;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("telefones")
public class TelefoneResource {

    @Inject
    private TelefoneService service;
    @Inject
    private LongaMemoria longaMemoria;
    @Inject
    private CurtaMemoria curtaMemoria;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("teste")
    public Response teste() {
        return Response.status(200).entity("Curta: "+curtaMemoria.getCont()+ ". "
                + " Longa: "+longaMemoria.getCont() ).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(200).entity(service.findAll()).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("limite")
    public Response findAll(@QueryParam("limit") int limit) {
        return Response.status(200).entity(service.findAll(limit)).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.status(200).entity(service.findByNome(nome)).build();
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
    public Response update(@PathParam("id") int id, Telefone telefone) {
        Telefone retorno = service.update(id, telefone);
        if (retorno != null) {
            return Response.status(200).entity(retorno).build();
        }
        return Response.status(404).entity("Não encontrado o telefone com o id: " + id).build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") int id) {
        Telefone retorno = service.find(id);
        if (retorno != null) {
            return Response.status(200).entity(retorno).build();
        }
        return Response.status(404).entity("Não encontrado o telefone com o id: " + id).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response create(Telefone telefone) {
        return Response.status(201).entity(service.create(telefone)).build();
    }
}
