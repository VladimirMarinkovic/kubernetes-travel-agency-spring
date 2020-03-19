package vlada.springboot.walter.exception;

public class SpringLoginException extends RuntimeException {
    public SpringLoginException(String poruka) {super(poruka); }
}
