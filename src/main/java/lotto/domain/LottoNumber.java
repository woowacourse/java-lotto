package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumber {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;
    private static final int LOTTO_NUMBER_START_INDEX = 0;
    private static final int LOTTO_NUMBER_END_INDEX = 6;

    private static final List<LottoNumber> LOTTO_NUMBERS;

    private final int number;

    static {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = LOWER_BOUND; i < UPPER_BOUND; i++) {
            numbers.add(new LottoNumber(i));
        }
        LOTTO_NUMBERS = numbers;
    }

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public static List<LottoNumber> getShuffledNumbers() {
        Collections.shuffle(LOTTO_NUMBERS);
        return new ArrayList<>(LOTTO_NUMBERS.subList(LOTTO_NUMBER_START_INDEX, LOTTO_NUMBER_END_INDEX));
    }

    private void validateRange(int number) {
        if (isValidRange(number)) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 " + LOWER_BOUND + "~" + UPPER_BOUND + " 사이의 숫자만 가능합니다");
        }
    }

    private boolean isValidRange(int number) {
        return number < LOWER_BOUND || number > UPPER_BOUND;
    }

    public int getNumber() {
        return number;
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
}
