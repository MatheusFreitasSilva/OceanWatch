package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.dtos.ViaCepApiResponse;
import org.example.repositories.daos.EnderecoRepository;
import org.example.services.EnderecoService;

/**
 * Classe EnderecoResource que define os endpoints da API relacionados aos endereços.
 */
@Path("endereco")
public class EnderecoResource {
    // Repositório de endereços
    public EnderecoRepository enderecoRepository;
    // Serviço de endereços
    public EnderecoService enderecoService;

    /**
     * Construtor padrão da classe EnderecoResource.
     * Inicializa o repositório e o serviço de endereços.
     */
    public EnderecoResource() {
        enderecoRepository = new EnderecoRepository();
        enderecoService = new EnderecoService();
    }

    /**
     * Endpoint para recuperar um endereço pelo CEP.
     *
     * @param cep o CEP do endereço a ser recuperado.
     * @return um Response contendo o endereço em formato JSON, se encontrado, ou um status NOT_FOUND se não encontrado.
     * @throws Exception se ocorrer algum erro durante a busca do endereço.
     */
    @GET
    @Path("/cep/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("cep") String cep) throws Exception {
        var endereco = enderecoService.GetByCep(cep);
        return endereco.isPresent() ?
                Response.ok(endereco.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Endpoint para recuperar um id de um endereço pelo CEP.
     *
     * @param cep o CEP do endereço a ser recuperado.
     * @return um Response contendo o id do endereço em formato JSON, se encontrado, ou um status NOT_FOUND se não encontrado.
     * @throws Exception se ocorrer algum erro durante a busca do endereço.
     */
    @GET
    @Path("/getIdByCep/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetIdByCep(@PathParam("cep") String cep) throws Exception {
        var endereco_id = enderecoRepository.FindIdByCep(cep);
        if (endereco_id != 0){
            return Response.ok(endereco_id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint para criar um novo endereço.
     *
     * @param endereco o objeto ViaCepApiResponse representando o endereço a ser criado.
     * @return um Response com o status CREATED se o endereço for criado com sucesso, ou BAD_REQUEST se ocorrer algum erro.
     */
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
