package model.lottotickets.vo;

import java.util.List;

public class Number {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final String NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호는 1~45까지의 숫자로 입력하세요.";

    private final int number;

    public Number(final int number) {
        this.number = checkNumberInLottoRange(number);
    }

    private int checkNumberInLottoRange(final int number) {
        if (isNumberNotInLottoRange(number)) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR_MESSAGE);
        }
        return number;
    }

    private boolean isNumberNotInLottoRange(final int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }

    public boolean contain(final List<Integer> WinningNumbers) {
        return WinningNumbers.contains(number);
    }

    public boolean contain(final int bonusNumber) {
        return number == bonusNumber;
    }

    public int getNumber() {
        return number;
    }
}
