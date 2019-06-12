package lotto.domain.exception;

import java.util.InputMismatchException;

public class InvalidWinnigLottoException extends RuntimeException {
    public InvalidWinnigLottoException(NumberFormatException e) {
        super(e.getMessage() + " -> 당첨번호에서 정수외의 문자가 입력되었습니다.");
    }

    public InvalidWinnigLottoException(InputMismatchException e) {
        super(e.getMessage() + " -> 당첨번호에서 정수외의 수가 입력되었습니다.");
    }
}
