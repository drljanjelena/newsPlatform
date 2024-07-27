package com.example.projekatvebbek.resources;

import com.example.projekatvebbek.entities.KomentarReakcija;
import com.example.projekatvebbek.services.KomentarReakcijaService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/komentariReakcije")
public class KomentarReakcijaResource {

    @Inject
    private KomentarReakcijaService komentarReakcijaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.komentarReakcijaService.allKomentariReakcije()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public KomentarReakcija create(@Valid KomentarReakcija komentarReakcija) {
        return this.komentarReakcijaService.addKomentarReakcija(komentarReakcija);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public KomentarReakcija find(@PathParam("id") Integer id) {
        return this.komentarReakcijaService.findKomentarReakcija(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.komentarReakcijaService.deleteKomentarReakcija(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, KomentarReakcija komentarReakcija) {
        return Response.ok(komentarReakcijaService.editKomentarReakcija(id, komentarReakcija)).build();
    }

}
