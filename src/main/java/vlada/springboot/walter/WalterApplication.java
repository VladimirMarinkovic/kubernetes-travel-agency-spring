package vlada.springboot.walter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import vlada.springboot.walter.dto.RegistracijaZahtev;
import vlada.springboot.walter.model.Korisnik;
import vlada.springboot.walter.repository.KorisnikRepository;
import vlada.springboot.walter.service.AuthService;

import java.util.Optional;

@SpringBootApplication
public class WalterApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WalterApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WalterApplication.class, args);
    }


    @Autowired
    AuthService authService;
    @Autowired
    KorisnikRepository korisnikRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<Korisnik> korisnik = korisnikRepository.findByUserName("admin");
        if(!korisnik.isPresent()) {
            authService.registruj(new RegistracijaZahtev("admin", "admin@mail.com", "admin"));
        }

    }


}
