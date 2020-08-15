package com.exemplo.resource;

import com.exemplo.connection.ConnectionUtil;
import com.exemplo.modelo.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

@Path("telefones-v2")
public class TelefoneResourceV2 {

    private List<Telefone> banco = new ArrayList<>();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try{
            Connection conn = ConnectionUtil.getCon();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM public.telefones");
            ResultSet resultado = stm.executeQuery();
            while(resultado.next()){
                Telefone aux = new Telefone();
                aux.setId(resultado.getInt("id"));
                aux.setNome(resultado.getString("nome"));
                banco.add(aux);
            }
        } catch (SQLException c){
            return Response.status(500).entity("SQL incorreto: " + c.getMessage()).build();
        }
        return Response.status(200).entity(banco).build();
    }
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id){
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

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") int id) {
        Optional<Telefone> retorno = banco.stream().filter(i -> i.getId() == id).findFirst();
        if (retorno.isPresent()) {
            return Response.status(200).entity(retorno.get()).build();
        }
        return Response.status(404).entity("Não encontrado o telefone com o id: " + id).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Telefone telefone) {
        try{
            Connection conn = ConnectionUtil.getCon();
            PreparedStatement stm = conn.prepareStatement("INSERT INTO public.telefones (id, nome) VALUES (nextval('telefones_id_seq'), ?)");
            stm.setString(1, telefone.getNome());
            stm.execute();
        } catch (SQLException c){
            return Response.status(500).entity("SQL incorreto: " + c.getMessage()).build();
        }
        return Response.status(201).entity(telefone).build();
    }
}
