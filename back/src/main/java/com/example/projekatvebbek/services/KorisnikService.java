package com.example.projekatvebbek.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.projekatvebbek.entities.Korisnik;
import com.example.projekatvebbek.repoitories.korisnici.KorisnikRepository;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class KorisnikService {
    public KorisnikService() {
        System.out.println(this);
    }

    @Inject
    private KorisnikRepository korisnikRepository;

    public Korisnik addKorisnik(Korisnik korisnik){
        return this.korisnikRepository.addKorisnik(korisnik);
    }
    public List<Korisnik> allKorisnici(){
        return this.korisnikRepository.allKorisnici();
    }
    public Korisnik findKorisnik(Integer id){
        return this.korisnikRepository.findKorisnik(id);
    }
    public Korisnik findKorisnikByEmail(String email) {return this.korisnikRepository.findKorisnikByEmail(email);};

        public void deleteKorisnik(Integer id){
        this.korisnikRepository.deleteKorisnik(id);
    }
    public Korisnik editKorisnik(Integer id,Korisnik korisnik){
        return this.korisnikRepository.editKorisnik(id,korisnik);
    }

    public String login(String email, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        Korisnik user = this.korisnikRepository.findKorisnikByEmail(email);
        if (user == null || !user.getHashLozinke().equals(hashedPassword) || !user.getStatus().equals("aktivan")) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000*10); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("tipKorisnika", user.getTipKorisnika())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
        String tipKorisnika = jwt.getClaim("tipKorisnika").asString();

        Korisnik user = this.korisnikRepository.findKorisnikByEmail(email);

        if (user == null){
            return false;
        }

        return tipKorisnika.equalsIgnoreCase("admin");
    }
}
