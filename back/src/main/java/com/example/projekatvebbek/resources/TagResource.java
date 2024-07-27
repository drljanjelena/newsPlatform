package com.example.projekatvebbek.resources;

import com.example.projekatvebbek.entities.Tag;
import com.example.projekatvebbek.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tagovi")
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.tagService.allTag()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag create(@Valid Tag tag) {
        return this.tagService.addTag(tag);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag find(@PathParam("id") Integer id) {
        return this.tagService.findTag(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.tagService.deleteTag(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Tag tag) {
        return Response.ok(tagService.editTag(id, tag)).build();
    }
}
