package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.dtos.Usuario;
import org.example.repositories.daos.UsuarioRepository;
import org.example.services.UsuarioService;
import java.util.List;

@Path("usuario")
public class UsuarioResource {

    public UsuarioRepository usuarioRepository;
    public UsuarioService usuarioServie;

    public UsuarioResource(){
        usuarioRepository = new UsuarioRepository();
        usuarioServie = new UsuarioService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> ReadAll(){
        return usuarioRepository.ReadAll();
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id){
        var usuario = usuarioRepository.FindById(id);
        return usuario.isPresent() ?
                Response.ok(usuario.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

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
}
