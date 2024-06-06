package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.dtos.Publicacao;
import org.example.repositories.daos.PublicacaoRepository;
import org.example.services.PublicacaoService;

import java.util.List;

/**
 * Classe PublicacaoResource que define os endpoints da API relacionados às publicações.
 */
@Path("publicacao")
public class PublicacaoResource {
    // Repositório de publicações
    public PublicacaoRepository publicacaoRepository;
    // Serviço de publicações
    public PublicacaoService publicacaoService;

    /**
     * Construtor padrão da classe PublicacaoResource.
     * Inicializa o repositório e o serviço de publicações.
     */
    public PublicacaoResource(){
        publicacaoRepository = new PublicacaoRepository();
        publicacaoService = new PublicacaoService();
    }

    /**
     * Endpoint para recuperar todas as publicações.
     *
     * @return uma lista de objetos Publicacao em formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Publicacao> ReadAll(){
        return publicacaoRepository.ReadAll();
    }

    /**
     * Endpoint para recuperar uma publicação pelo ID.
     *
     * @param id o ID da publicação a ser recuperada.
     * @return um Response contendo a publicação em formato JSON, se encontrada, ou um status NOT_FOUND se não encontrada.
     */
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id){
        var publicacao = publicacaoRepository.FindById(id);
        return publicacao.isPresent() ?
                Response.ok(publicacao.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Endpoint para criar uma nova publicação.
     *
     * @param publicacao o objeto Publicacao a ser criado.
     * @return um Response com o status CREATED se a publicação for criada com sucesso, ou BAD_REQUEST se ocorrer algum erro.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Create(Publicacao publicacao){
        try {
            publicacaoService.Create(publicacao);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Endpoint para atualizar uma publicação existente.
     *
     * @param id         o ID da publicação a ser atualizada.
     * @param publicacao o objeto Publicacao com os novos dados.
     * @return um Response com o status NO_CONTENT se a publicação for atualizada com sucesso, ou BAD_REQUEST se ocorrer algum erro.
     */
    @PUT
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Update(@PathParam("id") int id, Publicacao publicacao){
        try {
            publicacaoService.Update(id, publicacao);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Endpoint para deletar uma publicação pelo ID.
     *
     * @param id o ID do usuário a ser deletado.
     * @return um Response com o status NO_CONTENT se a publicação for deletado com sucesso, ou BAD_REQUEST se ocorrer algum erro.
     */
    @DELETE
    @Path("id/{id}")
    public Response Delete(@PathParam("id") int id){
        try {
            publicacaoRepository.DeleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
