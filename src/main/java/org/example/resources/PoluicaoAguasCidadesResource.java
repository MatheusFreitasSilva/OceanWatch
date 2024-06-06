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

/**
 * Classe PoluicaoAguasCidadesResource que define os endpoints da API relacionados às poluições das águas por cidade.
 */
@Path("poluicaoAguasCidades")
public class PoluicaoAguasCidadesResource {
    // Repositório de poluição das águas nas cidades
    PoluicaoAguasCidadesRepository poluicaoAguasCidadesRepository;

    /**
     * Construtor padrão da classe PoluicaoAguasCidadesResource.
     * Inicializa o repositório de poluição das águas nas cidades.
     */
    public PoluicaoAguasCidadesResource(){
        poluicaoAguasCidadesRepository = new PoluicaoAguasCidadesRepository();
    }

    /**
     * Endpoint para recuperar todas as informações de poluição das águas nas cidades.
     *
     * @return uma lista de informações de poluição das águas nas cidades em formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PoluicaoAguasCidades> ReadAll(){
        return poluicaoAguasCidadesRepository.ReadAll();
    }

    /**
     * Endpoint para recuperar uma informação de poluição das águas nas cidades pelo ID.
     *
     * @param id o ID da informação de poluição das águas nas cidades a ser recuperada.
     * @return um Response contendo a informação de poluição das águas nas cidades em formato JSON, se encontrada,
     *         ou um status NOT_FOUND se não encontrada.
     */
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
