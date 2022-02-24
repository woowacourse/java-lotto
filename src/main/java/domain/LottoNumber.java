package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static constant.LottoConstant.*;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final String INVALID_LOTTO_NUMBER_RANGE = "번호는 1부터 45 사이여야 합니다.";
    private static final String INVALID_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private static final LottoNumber[] LOTTO_NUMBERS = new LottoNumber[MAXIMUM_LOTTO_NUMBER + 1];

    private final int number;

    static {
        IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .forEach(number -> LOTTO_NUMBERS[number] = new LottoNumber(number));
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber getInstance(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE);
        }
        return LOTTO_NUMBERS[number];
    }

    public static LottoNumber createBonus(int inputBonusNumber, LottoTicketNumbers winningNumbers) {
        LottoNumber bonusNumber = LottoNumber.getInstance(inputBonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER);
        }
        return bonusNumber;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return this.number - other.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public static List<LottoNumber> getNumbers() {
        return Arrays.asList(LOTTO_NUMBERS);
    }

    public int getNumber() {
        return number;
    }
}
