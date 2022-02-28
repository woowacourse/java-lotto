package exception;

public class InvalidLottoNumbersSizeException extends IllegalArgumentException {

    public InvalidLottoNumbersSizeException() {
        super("로또 번호 갯수는 6개여야 합니다.");
    }
}
