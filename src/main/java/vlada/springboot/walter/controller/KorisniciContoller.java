package vlada.springboot.walter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vlada.springboot.walter.dto.KorisnikDto;
import vlada.springboot.walter.dto.RegistracijaZahtev;
import vlada.springboot.walter.repository.KorisnikRepository;
import vlada.springboot.walter.service.KorisniciService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/korisnici")
public class KorisniciContoller {

    @Autowired
    KorisniciService korisniciService;
    @Autowired
    KorisnikRepository korisnikRepository;

    @GetMapping("/svi-korisnici")
    public ResponseEntity<List<KorisnikDto>> prikaziSveKorisnike() {
        return new ResponseEntity<>(korisniciService.nadjiSveKorisnike(), HttpStatus.OK);
    }


    @GetMapping("/korisnik/{id}")
    public ResponseEntity<KorisnikDto> prikaziKorisnika(@PathVariable Long id) {
        return new ResponseEntity<>(korisniciService.nadjiKorisnika(id), HttpStatus.OK);
    }

    @PutMapping("/korisnik/{id}")
    public ResponseEntity<KorisnikDto> izmeniKorisnika(@PathVariable Long id,  @RequestBody KorisnikDto korisnikDto) {
        return new ResponseEntity<KorisnikDto>(korisniciService.izmeniKorisnika(id, korisnikDto), HttpStatus.OK);
    }

    @DeleteMapping("/korisnik/{id}")
    public void obrisiKorisnika(@PathVariable Long id) {
        korisniciService.obrisiKorisnikaZaId(id);
    }


    @PostMapping("/registruj-zaposlenog")
    public ResponseEntity registracija(@RequestBody RegistracijaZahtev registracijaZahtev) {
        korisniciService.sacuvajKorisnika(registracijaZahtev);
        return new ResponseEntity(HttpStatus.OK);
    }
}
