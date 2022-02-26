package lotto.model.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
 * 로또 번호로 쓸 수 있는 int에 대한 Wrapper Class
 */
public class LottoNumber implements Comparable<LottoNumber> {
    private static final List<LottoNumber> NOMINEE_NUMBERS;
    private static final String ERROR_BOUND = "[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요";
    private static final String ERROR_TYPE = "[ERROR] 로또 번호는 숫자로만 입력해주세요";
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    static {
        NOMINEE_NUMBERS = new ArrayList<>();
        for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
            NOMINEE_NUMBERS.add(LottoNumber.from(String.valueOf(number)));
        }
    }

    private LottoNumber(int number) {
        checkBound(number);
        this.number = number;
    }

    private void checkBound(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_BOUND);
        }
    }

    public static LottoNumber from(String input) {
        try {
            return new LottoNumber(Integer.parseInt(input.trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public static LottoNumber draw() {
        Collections.shuffle(NOMINEE_NUMBERS);
        return NOMINEE_NUMBERS.get(0);
    }

    public boolean match(int number) {
        return this.number == number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(this.number, lottoNumber.number);
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
        return Objects.hash(number);
    }
}
