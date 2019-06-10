package lotto.model.exceptions;

public class IllegalLottoNumberException extends RuntimeException {
    private static String MESSAGE = "로또 넘버는 1이상 45이하 입니다!";

    public IllegalLottoNumberException() {
        super(MESSAGE);
    }
}
