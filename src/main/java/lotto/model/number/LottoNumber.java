package lotto.model.number;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * 로또 번호로 쓸 수 있는 int에 대한 Wrapper Class
 */
public class LottoNumber implements Comparable<LottoNumber> {
    private static final String ERROR_BOUND = "[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요";
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int value;

    public LottoNumber(int value) {
        checkBound(value);
        this.value = value;
    }

    private static void checkBound(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_BOUND);
        }
    }

    public static List<LottoNumber> ofAllNumbers() {
        List<LottoNumber> allNumbers = new ArrayList<>();
        for (int value = MIN_NUMBER; value <= MAX_NUMBER; value++) {
            allNumbers.add(new LottoNumber(value));
        }
        return allNumbers;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(this.value, lottoNumber.value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) object;
        return this.compareTo(lottoNumber) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
