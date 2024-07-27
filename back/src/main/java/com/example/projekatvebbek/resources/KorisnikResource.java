package com.example.projekatvebbek.resources;

import com.example.projekatvebbek.entities.Korisnik;
import com.example.projekatvebbek.requests.LoginRequest;
import com.example.projekatvebbek.services.KorisnikService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/korisnici")
public class KorisnikResource {

    @Inject
    private KorisnikService korisnikService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.korisnikService.allKorisnici()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik create(@Valid Korisnik korisnik) {
        return this.korisnikService.addKorisnik(korisnik);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik find(@PathParam("id") Integer id) {
        return this.korisnikService.findKorisnik(id);
    }

    @GET
    @Path("/byEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik findByEmail(@PathParam("email") String email) {
        return this.korisnikService.findKorisnikByEmail(email);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.korisnikService.deleteKorisnik(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Korisnik korisnik) {
        return Response.ok(korisnikService.editKorisnik(id, korisnik)).build();
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.korisnikService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }
}
