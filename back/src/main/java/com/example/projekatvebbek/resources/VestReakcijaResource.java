package com.example.projekatvebbek.resources;

import com.example.projekatvebbek.entities.Tag;
import com.example.projekatvebbek.entities.VestReakcija;
import com.example.projekatvebbek.services.VestReakcijaService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vestiReakcije")
public class VestReakcijaResource {

    @Inject
    private VestReakcijaService vestReakcijaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.vestReakcijaService.allVestReakcije()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public VestReakcija create(@Valid VestReakcija vestReakcija) {
        return this.vestReakcijaService.addVestReakciju(vestReakcija);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public VestReakcija find(@PathParam("id") Integer id) {
        return this.vestReakcijaService.findVestReakcija(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.vestReakcijaService.deleteVestReakcija(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, VestReakcija vestReakcija) {
        return Response.ok(vestReakcijaService.editVestReakcija(id, vestReakcija)).build();
    }
}
