package lotto.domain.lotto;

class LottoNumber implements Comparable<LottoNumber> {

    private static String WRONG_LOTTO_NUMBER = "로또는 1부터 45까지의 숫자만 가능합니다.";
    static final int MINIMUM_LOTTO_NUMBER = 1;
    static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final int number;

    LottoNumber(int number) {
        validateLottoNumber(number);
        this.number = number;
    }

    private void validateLottoNumber(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(WRONG_LOTTO_NUMBER);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNumber targetNumber) {
        return this.number - targetNumber.number;
    }
}
