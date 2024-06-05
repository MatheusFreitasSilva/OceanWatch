package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.dtos.Publicacao;
import org.example.repositories.daos.PublicacaoRepository;

import java.util.List;

@Path("publicacao")
public class PublicacaoResource {

    public PublicacaoRepository publicacaoRepository;

    public PublicacaoResource(){
        publicacaoRepository = new PublicacaoRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Publicacao> ReadAll(){
        return publicacaoRepository.ReadAll();
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id){
        var publicacao = publicacaoRepository.FindById(id);
        return publicacao.isPresent() ?
                Response.ok(publicacao.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Create(Publicacao publicacao){
        try {
            publicacaoRepository.Create(publicacao);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Update(@PathParam("id") int id, Publicacao publicacao){
        try {
            publicacaoRepository.Update(id, publicacao);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
