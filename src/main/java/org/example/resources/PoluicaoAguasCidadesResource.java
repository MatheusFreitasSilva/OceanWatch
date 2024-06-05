package org.example.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.dtos.PoluicaoAguasCidades;
import org.example.repositories.daos.PoluicaoAguasCidadesRepository;

import java.util.List;

@Path("poluicaoAguasCidades")
public class PoluicaoAguasCidadesResource {

    PoluicaoAguasCidadesRepository poluicaoAguasCidadesRepository;

    public PoluicaoAguasCidadesResource(){
        poluicaoAguasCidadesRepository = new PoluicaoAguasCidadesRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PoluicaoAguasCidades> ReadAll(){
        return poluicaoAguasCidadesRepository.ReadAll();
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id){
        var informacaoPoluicao = poluicaoAguasCidadesRepository.FindById(id);
        return informacaoPoluicao.isPresent() ?
                Response.ok(informacaoPoluicao.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
