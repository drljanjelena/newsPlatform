package com.example.projekatvebbek.resources;

import com.example.projekatvebbek.entities.Kategorija;
import com.example.projekatvebbek.services.KategorijaService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/kategorije")
public class KategorijaResource {

    @Inject
    private KategorijaService kategorijaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.kategorijaService.allKategorije()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija create(@Valid Kategorija kategorija) {
        return this.kategorijaService.addKategorija(kategorija);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija find(@PathParam("id") Integer id) {
        return this.kategorijaService.findKategoriju(id);
    }

    @GET
    @Path("/byIme/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija findByIme(@PathParam("ime") String ime) {
        return this.kategorijaService.findKategorijuByIme(ime);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.kategorijaService.deleteKategoriju(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Kategorija kategorija) {
            return Response.ok(kategorijaService.editKategorija(id, kategorija)).build();
    }

}
