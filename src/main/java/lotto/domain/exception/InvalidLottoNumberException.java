package lotto.domain.exception;

public class InvalidLottoNumberException extends RuntimeException {
    public InvalidLottoNumberException(int number) {
        super(number + " -> 1부터 45까지의 수가 아닙니다.");
    }
}
