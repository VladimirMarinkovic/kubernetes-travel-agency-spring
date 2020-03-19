package vlada.springboot.walter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vlada.springboot.walter.dto.KorisnikDto;
import vlada.springboot.walter.model.Korisnik;

@Mapper(componentModel = "spring")
public interface KorisnikMapper {


    @Mapping(source = "userName", target = "username")
    KorisnikDto korisnikUKorisnikDto(Korisnik korisnik);

    @Mapping(source = "username", target = "userName")
    Korisnik korisnikDtoUKorisnik(KorisnikDto korisnikDto);

}
