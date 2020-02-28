package lotto.domain;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final String LOTTO_NUMBER_OUTOF_BOUND = "범위를 벗어난 로또 숫자입니다.";
    public static final int MIN = 1;
    public static final int MAX = 45;

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validate(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validate(int lottoNumber) {
        if (lottoNumber < MIN || lottoNumber > MAX) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUTOF_BOUND);
        }
    }

    public boolean isEqualTo(int number) {
        return lottoNumber == number;
    }

    @Override
    public String toString() {
        return Integer.toString(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber anotherLottoNumber) {
        return this.lottoNumber - anotherLottoNumber.lottoNumber;
    }

}
