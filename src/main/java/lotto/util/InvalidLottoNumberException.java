package lotto.util;

public class InvalidLottoNumberException extends RuntimeException {
    public InvalidLottoNumberException(int lottoNumber) {
        super(lottoNumber + " : 로또 번호는 1 ~ 45를 벗어날 수 없습니다.");
    }
}
