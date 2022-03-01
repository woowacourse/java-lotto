package exception;

public class DuplicatedLottoNumbersException extends IllegalArgumentException {

    public DuplicatedLottoNumbersException() {
        super("중복된 로또 번호는 입력할 수 없습니다.");
    }
}
