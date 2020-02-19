package domain;

public class LottoNumber {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private int number;

    public LottoNumber(String number) {
        checkNotNumber(number);
        int numberIntegerValue = Integer.parseInt(number);
        checkLottoRange(numberIntegerValue);
        this.number = numberIntegerValue;
    }

    public LottoNumber(int number) {
        checkLottoRange(number);
        this.number = number;
    }

    private void checkLottoRange(int number) {
        if (isNotLottoNumber(number)) {
            throw new IllegalArgumentException("로또 숫자 범위를 넘어섰습니다.");
        }
    }

    private boolean isNotLottoNumber(int number) {
        return number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER;
    }

    private void checkNotNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("로또 넘버는 숫자여야 합니다. 입력한 문자 : %s", number));
        }
    }

    @Override
    public boolean equals(Object lottoNumber) {
        return this.number == ((LottoNumber)lottoNumber).number;
    }
}
