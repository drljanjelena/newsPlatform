package com.example.projekatvebbek.resources;

import com.example.projekatvebbek.entities.VestTag;
import com.example.projekatvebbek.services.VestTagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vestiTagovi")
public class VestTagResource {

    @Inject
    private VestTagService vestTagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.vestTagService.allVestTag()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public VestTag create(@Valid VestTag vestTag) {
        return this.vestTagService.addVestTag(vestTag);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public VestTag find(@PathParam("id") Integer id) {
        return this.vestTagService.findVestTag(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.vestTagService.deleteVestTag(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, VestTag vestTag) {
        return Response.ok(vestTagService.editVestTag(id, vestTag)).build();
    }
}
