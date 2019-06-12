package lotto.domain.exception;

public class InvalidLottoException extends RuntimeException {
    public InvalidLottoException(int size) {
        super(String.format("현재 로또 크기 : %d -> 로또 범위는 6개여야 합니다.", size));
    }

    public InvalidLottoException(int checkValidateSize, int lottoNumbersSize) {
        super(String.format(
                "중복을 뺀 로또의 개수 : %d, 중복을 포함한 로또의 개수 : %d" +
                        " -> 중복된 숫자가 있습니다."
                , checkValidateSize, lottoNumbersSize));
    }
}
