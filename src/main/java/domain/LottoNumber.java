package domain;

public class LottoNumber {

    public static final String LOTTO_NUMBER_OUT_OF_BOUND = "로또번호는 1~45 사이값으로 생성할 수 있습니다";
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        validateLottoNumberBound(number);
        this.number = number;
    }

    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }
}
