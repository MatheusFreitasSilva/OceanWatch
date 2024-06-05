package org.example.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.dtos.ProducaoPlasticoGlobal;
import org.example.repositories.daos.ProducaoPlasticoGlobalRepository;

import java.util.List;

@Path("producaoPlasticoGlobal")
public class ProducaoPlasticoGlobalResource {

    ProducaoPlasticoGlobalRepository producaoPlasticoGlobalRepository;

    public ProducaoPlasticoGlobalResource(){
        producaoPlasticoGlobalRepository = new ProducaoPlasticoGlobalRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProducaoPlasticoGlobal> ReadAll(){
        return producaoPlasticoGlobalRepository.ReadAll();
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id){
        var informacaoProducao = producaoPlasticoGlobalRepository.FindById(id);
        return informacaoProducao.isPresent() ?
                Response.ok(informacaoProducao.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
