package com.exemplo.resource;

import com.exemplo.modelo.Telefone;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

@Path("telefones-v1")
public class TelefoneResourceV1 {

    private static List<Telefone> banco = new ArrayList<>();

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") int id) {
        Optional<Telefone> retorno = banco.stream().filter(i -> i.getId() == id).findFirst();
        if (retorno.isPresent()) {
            return Response.status(200).entity(retorno.get()).build();
        }
        return Response.status(404).entity("Não foi encontrado o telefone com o id: " + id).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(200).entity(banco).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Telefone telefone) {
        int id = 1;
        for (Telefone aux : banco) {
            int idAux = 0;
            if (idAux < aux.getId()) {                
                idAux = aux.getId();
                id = idAux;
            }
        }
        telefone.setId(++id);
        banco.add(telefone);
        return Response.status(201).entity(telefone).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Optional<Telefone> retorno = banco.stream().filter(i -> i.getId() == id).findFirst();
        if (retorno.isPresent()) {
            banco.remove(retorno.get());
            return Response.status(204).build();
        }
        return Response.status(404).entity("Não encontrado o telefone com o id: " + id).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Telefone telefone) {
        Optional<Telefone> retorno = banco.stream().filter(i -> i.getId() == id).findFirst();
        if (retorno.isPresent()) {
            banco.remove(retorno.get());
            telefone.setId(id);
            banco.add(telefone);
            return Response.status(200).entity(telefone).build();
        }
        return Response.status(404).entity("Não encontrado o telefone com o id: " + id).build();
    }
}
