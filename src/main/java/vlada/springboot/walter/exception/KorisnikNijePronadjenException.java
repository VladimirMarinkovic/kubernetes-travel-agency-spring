package vlada.springboot.walter.exception;

public class KorisnikNijePronadjenException extends  RuntimeException{

    public KorisnikNijePronadjenException(String poruka) {
        super(poruka);
    }
}
