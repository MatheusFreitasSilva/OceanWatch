package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.dtos.ViaCepApiResponse;
import org.example.repositories.daos.EnderecoRepository;
import org.example.services.EnderecoService;


@Path("endereco")
public class EnderecoResource {

    public EnderecoRepository enderecoRepository;
    public EnderecoService enderecoService;

    public EnderecoResource() {
        enderecoRepository = new EnderecoRepository();
        enderecoService = new EnderecoService();
    }

    @GET
    @Path("/cep/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("cep") String cep) throws Exception {
        var endereco = enderecoService.GetByCep(cep);
        return endereco.isPresent() ?
                Response.ok(endereco.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Create(ViaCepApiResponse endereco) {
        try {
            enderecoRepository.Create(endereco);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
