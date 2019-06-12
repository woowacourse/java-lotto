package lotto.domain.exception;

import lotto.domain.LottoNumber;

public class InvalidBounusBallException extends RuntimeException {
    public InvalidBounusBallException(String number) {
        super(number + "-> 옳바르지 않은 입력입니다.");
    }

    public InvalidBounusBallException(LottoNumber number) {
        super(number.getNumber() + "-> 당첨번호와 중복된 정수가 있습니다.");
    }
}
