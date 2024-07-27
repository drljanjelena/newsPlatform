package com.example.projekatvebbek.resources;


import com.example.projekatvebbek.entities.Komentar;
import com.example.projekatvebbek.services.KomentarService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/komentari")
public class KomentarResource {

    @Inject
    private KomentarService komentarService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.komentarService.allKomentari()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar create(@Valid Komentar komentar) {
        return this.komentarService.addKomentar(komentar);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar find(@PathParam("id") Integer id) {
        return this.komentarService.findKomentar(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.komentarService.deleteKomentar(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Komentar komentar) {
        return Response.ok(komentarService.editKomentar(id, komentar)).build();
    }

    @GET
    @Path("/byVest/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByVest(@PathParam("id") Integer id) {
        return Response.ok(this.komentarService.findByVest(id)).build();
    }
}
