package exception;

public class InvalidMatchCountException extends IllegalArgumentException {
    public InvalidMatchCountException() {
        super("일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.");
    }
}
