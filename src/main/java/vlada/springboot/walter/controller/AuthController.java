package vlada.springboot.walter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vlada.springboot.walter.dto.LoginZahtev;
import vlada.springboot.walter.dto.RegistracijaZahtev;
import vlada.springboot.walter.security.AuthenticationResponse;
import vlada.springboot.walter.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/proba")
    public String proba() {
        return "PROBA KUBERNETES!";
    }

    @PostMapping("/registracija")
    public ResponseEntity registracija(@RequestBody RegistracijaZahtev registracijaZahtev) {
        authService.registruj(registracijaZahtev);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginZahtev loginZahtev) {
        return authService.login(loginZahtev);
    }
}
