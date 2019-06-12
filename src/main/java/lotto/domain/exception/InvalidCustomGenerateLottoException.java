package lotto.domain.exception;

import java.util.InputMismatchException;

public class InvalidCustomGenerateLottoException extends RuntimeException {
    public InvalidCustomGenerateLottoException(InputMismatchException e) {
        super(e.getMessage() + " -> 수동입력에서 정수 외의 수를 입력했습니다.");
    }

    public InvalidCustomGenerateLottoException(NumberFormatException e) {
        super(e.getMessage() + " -> 수동입력에서 잘못된 문자를 입력했습니다.");
    }


}
