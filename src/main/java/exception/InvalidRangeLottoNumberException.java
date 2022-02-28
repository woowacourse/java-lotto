package exception;

public class InvalidRangeLottoNumberException extends IllegalArgumentException {
    public InvalidRangeLottoNumberException() {
        super("로또 번호는 1 ~ 45 사이여야 합니다.");
    }
}
