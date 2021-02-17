package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    private static final int START = 1;
    private static final int END = 45;
    private final int value;

    public LottoNumber(int number) {
        if (number < START || number > END)
            throw new IllegalArgumentException("[Error] 로또 번호는 1부터 45까지 입니다.");
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
