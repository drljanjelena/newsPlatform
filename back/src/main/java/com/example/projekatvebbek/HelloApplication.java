package com.example.projekatvebbek;
import com.example.projekatvebbek.repoitories.kategorije.KategorijaRepository;
import com.example.projekatvebbek.repoitories.kategorije.MySqlKategorijaRepository;
import com.example.projekatvebbek.repoitories.komentarReakcija.KomentarReakcijaRepository;
import com.example.projekatvebbek.repoitories.komentarReakcija.MySqlKomentarReakcijaRepository;
import com.example.projekatvebbek.repoitories.komentari.KomentarRepository;
import com.example.projekatvebbek.repoitories.komentari.MySqlKomentarRepository;
import com.example.projekatvebbek.repoitories.korisnici.KorisnikRepository;
import com.example.projekatvebbek.repoitories.korisnici.MySqlKorisnikRepository;
import com.example.projekatvebbek.repoitories.tag.MySqlTagRepository;
import com.example.projekatvebbek.repoitories.tag.TagRepository;
import com.example.projekatvebbek.repoitories.vestReakcija.MySqlVestReakcijaReposiroty;
import com.example.projekatvebbek.repoitories.vestReakcija.VestReakcijaReposiroty;
import com.example.projekatvebbek.repoitories.vesti.MySqlVestRepository;
import com.example.projekatvebbek.repoitories.vesti.VestRepository;
import com.example.projekatvebbek.repoitories.vestiagovi.MySqlVestTagRepository;
import com.example.projekatvebbek.repoitories.vestiagovi.VestTagRepository;
import com.example.projekatvebbek.services.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlKorisnikRepository.class).to(KorisnikRepository.class).in(Singleton.class);
                this.bind(MySqlKategorijaRepository.class).to(KategorijaRepository.class).in(Singleton.class);
                this.bind(MySqlKomentarRepository.class).to(KomentarRepository.class).in(Singleton.class);
                this.bind(MySqlKomentarReakcijaRepository.class).to(KomentarReakcijaRepository.class).in(Singleton.class);
                this.bind(MySqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySqlVestRepository.class).to(VestRepository.class).in(Singleton.class);
                this.bind(MySqlVestTagRepository.class).to(VestTagRepository.class).in(Singleton.class);
                this.bind(MySqlVestReakcijaReposiroty.class).to(VestReakcijaReposiroty.class).in(Singleton.class);

                this.bindAsContract(KorisnikService.class);
                this.bindAsContract(KategorijaService.class);
                this.bindAsContract(KomentarService.class);
                this.bindAsContract(KomentarReakcijaService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(VestService.class);
                this.bindAsContract(VestTagService.class);
                this.bindAsContract(VestReakcijaService.class);

            }
        };
        register(binder);

        packages("com.example.projekatvebbek");
    }
}
