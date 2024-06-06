package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.dtos.Usuario;
import org.example.repositories.daos.UsuarioRepository;
import org.example.services.UsuarioService;
import java.util.List;

/**
 * Classe UsuarioResource que define os endpoints da API relacionados aos usuários.
 */
@Path("usuario")
public class UsuarioResource {
    // Repositório de usuários
    public UsuarioRepository usuarioRepository;
    // Serviço de usuário
    public UsuarioService usuarioServie;

    /**
     * Construtor padrão da classe UsuarioResource.
     * Inicializa o repositório de usuários e o serviço de usuário.
     */
    public UsuarioResource(){
        usuarioRepository = new UsuarioRepository();
        usuarioServie = new UsuarioService();
    }

    /**
     * Endpoint para recuperar todos os usuários.
     *
     * @return uma lista de objetos Usuario em formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> ReadAll(){
        return usuarioRepository.ReadAll();
    }

    /**
     * Endpoint para recuperar um usuário pelo ID.
     *
     * @param id o ID do usuário a ser recuperado.
     * @return um Response contendo o usuário em formato JSON, se encontrado, ou um status NOT_FOUND se não encontrado.
     */
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id){
        var usuario = usuarioRepository.FindById(id);
        return usuario.isPresent() ?
                Response.ok(usuario.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Endpoint para criar um novo usuário.
     *
     * @param usuario o objeto Usuario a ser criado.
     * @return um Response com o status CREATED se o usuário for criado com sucesso, ou BAD_REQUEST se ocorrer algum erro.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Create(Usuario usuario){
        try {
            usuarioServie.Create(usuario);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Endpoint para atualizar um usuário existente.
     *
     * @param id      o ID do usuário a ser atualizado.
     * @param usuario o objeto Usuario com os novos dados.
     * @return um Response com o status NO_CONTENT se o usuário for atualizado com sucesso, ou BAD_REQUEST se ocorrer algum erro.
     */
    @PUT
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Update(@PathParam("id") int id, Usuario usuario){
        try {
            usuarioServie.Update(id, usuario);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Endpoint para deletar um usuário pelo ID.
     *
     * @param id o ID do usuário a ser deletado.
     * @return um Response com o status NO_CONTENT se o usuário for deletado com sucesso, ou BAD_REQUEST se ocorrer algum erro.
     */
    @DELETE
    @Path("id/{id}")
    public Response Delete(@PathParam("id") int id){
        try {
            usuarioRepository.DeleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
