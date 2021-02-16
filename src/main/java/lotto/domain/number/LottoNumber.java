package lotto.domain.number;

public class LottoNumber extends Number{

    private static int NUMBER_MINIMUM = 1;
    private static int NUMBER_MAXIMUM = 45;

    public LottoNumber(String value) {
        super(value);
        validateRange();
    }

    public LottoNumber(int value) {
        this(String.valueOf(value));
    }

    private void validateRange() {
        if (value < NUMBER_MINIMUM || value > NUMBER_MAXIMUM) {
            throw new IllegalArgumentException("범위 밖의 로또 번호 입니다.");
        }
    }
}
