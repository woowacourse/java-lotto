package lotto.domain;

public class LottoNumber {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final String NUMBER_RANGE_ERROR = "로또 번호 범위가 벗어났습니다.";

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        if (lottoNumber > MAXIMUM_NUMBER || lottoNumber < MINIMUM_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
        this.lottoNumber = lottoNumber;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }
}
