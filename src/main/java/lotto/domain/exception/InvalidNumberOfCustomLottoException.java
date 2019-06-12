package lotto.domain.exception;

public class InvalidNumberOfCustomLottoException extends RuntimeException {
    public InvalidNumberOfCustomLottoException(String number) {
        super(number + " -> 잘못된 입력입니다.");
    }

    public InvalidNumberOfCustomLottoException(int number) {
        super(number + " -> 0이상의 수만 입력하세요.");
    }

    public InvalidNumberOfCustomLottoException(int currentNumber, int maxNumber) {
        super(String.format("현재 입력: %d, 최대 값 : %d -> 총 개수보다 넘을 수는 없습니다.", currentNumber, maxNumber));
    }
}
