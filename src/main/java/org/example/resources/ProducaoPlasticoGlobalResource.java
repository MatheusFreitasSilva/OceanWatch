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

/**
 * Classe ProducaoPlasticoGlobalResource que define os endpoints da API relacionados a Produção de Plástico Global.
 */
@Path("producaoPlasticoGlobal")
public class ProducaoPlasticoGlobalResource {
    // Repositório de produção global de plástico
    ProducaoPlasticoGlobalRepository producaoPlasticoGlobalRepository;

    /**
     * Construtor padrão da classe ProducaoPlasticoGlobalResource.
     * Inicializa o repositório de produção global de plástico.
     */
    public ProducaoPlasticoGlobalResource(){
        producaoPlasticoGlobalRepository = new ProducaoPlasticoGlobalRepository();
    }

    /**
     * Endpoint para recuperar todas as informações de produção global de plástico.
     *
     * @return uma lista de informações de produção global de plástico em formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProducaoPlasticoGlobal> ReadAll(){
        return producaoPlasticoGlobalRepository.ReadAll();
    }

    /**
     * Endpoint para recuperar uma informação de produção global de plástico pelo ID.
     *
     * @param id o ID da informação de produção global de plástico a ser recuperada.
     * @return um Response contendo a informação de produção global de plástico em formato JSON, se encontrada,
     *         ou um status NOT_FOUND se não encontrada.
     */
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
