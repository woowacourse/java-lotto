package lotto.exception;

public class InvalidSizeLottoException extends RuntimeException {
    public InvalidSizeLottoException(int lottoSize) {
        super(lottoSize + " : 로또 번호는 6자리여야 합니다.");
    }
}
