package com.example.projekatvebbek.resources;


import com.example.projekatvebbek.entities.Vest;
import com.example.projekatvebbek.entities.VestReakcija;
import com.example.projekatvebbek.services.VestService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vesti")
public class VestResource {

    @Inject
    private VestService vestService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.vestService.allVesti()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vest create(@Valid Vest vest) {
        return this.vestService.addVest(vest);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest find(@PathParam("id") Integer id) {
        return this.vestService.findVest(id);
    }

    @GET
    @Path("/byKategorija/{kategorija}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVestiByKategorija(@PathParam("kategorija") Integer kategorijaId) {
        return Response.ok(this.vestService.getVestiByCategory(kategorijaId)).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.vestService.deleteVest(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Vest vest) {
        return Response.ok(vestService.editVest(id, vest)).build();
    }


}
