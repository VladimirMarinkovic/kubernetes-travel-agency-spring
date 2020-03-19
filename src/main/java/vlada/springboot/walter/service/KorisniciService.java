package vlada.springboot.walter.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vlada.springboot.walter.dto.KorisnikDto;
import vlada.springboot.walter.dto.RegistracijaZahtev;
import vlada.springboot.walter.exception.KorisnikNijePronadjenException;
import vlada.springboot.walter.mapper.KorisnikMapper;
import vlada.springboot.walter.model.Korisnik;
import vlada.springboot.walter.repository.KorisnikRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KorisniciService {


    @Autowired
    private  KorisnikRepository korisnikRepository;
    @Autowired
    private KorisnikMapper korisnikMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }




    public List<KorisnikDto> nadjiSveKorisnike() {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        return korisnici.stream().map(korisnikMapper::korisnikUKorisnikDto).collect(Collectors.toList());
    }


    public KorisnikDto nadjiKorisnika(Long id) {
        Optional<Korisnik> optionalKorisnik = korisnikRepository.findById(id);
        Korisnik korisnik = optionalKorisnik.orElseThrow(()-> new KorisnikNijePronadjenException("Nije pronadjen korisnik za id: " +id));
        return korisnikMapper.korisnikUKorisnikDto(korisnik);
//        Korisnik korisnik = korisnikRepository.findById(id).orElseThrow(() -> new KorisnikNijePronadjenException("Za id" + id));
//        return korisnikMapper.korisnikUKorisnikDto(korisnik);

    }

    public KorisnikDto izmeniKorisnika(Long id, KorisnikDto korisnikDto) {
        Korisnik korisnik = korisnikRepository.findById(id).orElseThrow(()-> new KorisnikNijePronadjenException("Nije pronadjen korisnik za izmenu sa id: "+id));
        korisnik.setUserName(korisnikDto.getUsername());
        korisnik.setEmail(korisnikDto.getEmail());
        korisnikRepository.save(korisnik);
        return korisnikMapper.korisnikUKorisnikDto(korisnik);

    }

    public void obrisiKorisnikaZaId(Long id) {
        Korisnik korisnik = korisnikRepository.findById(id).orElseThrow(()-> new KorisnikNijePronadjenException("Nije pronadjen korisnik za brisanje sa id: "+id));
        korisnikRepository.delete(korisnik);
    }

    public void  sacuvajKorisnika(RegistracijaZahtev registracijaZahtev) {
        Korisnik korisnik = new Korisnik();
        korisnik.setUserName(registracijaZahtev.getUsername());
        korisnik.setEmail(registracijaZahtev.getEmail());
        korisnik.setPassword(encodePassword(registracijaZahtev.getPassword()));

        korisnikRepository.save(korisnik);
    }








//    @Autowired
//    private ModelMapper modelMapper;

//    private KorisnikDto mapKorisnikUZahtev(Korisnik korisnik) {
//        return modelMapper.map(korisnik, KorisnikDto.class);
//    }
//
//    private Korisnik mapZahtevUKorisnik(KorisnikDto korisnikDto) {
//        return modelMapper.map(korisnikDto, Korisnik.class);
//    }


//    private KorisnikDto mapKorisnikUZahtev(Korisnik korisnik) {
//        KorisnikDto korisnikDto = new KorisnikDto();
//        korisnikDto.setUsername(korisnik.getUserName());
//        korisnikDto.setEmail(korisnik.getEmail());
//        return korisnikDto;
//    }
//
//    private Korisnik mapZahtevUKorisnik(KorisnikDto korisnikDto) {
//        Korisnik korisnik = new Korisnik();
//        korisnik.setUserName(korisnikDto.getUsername());
//        korisnik.setEmail(korisnik.getEmail());
//        return korisnik;
//    }


}
