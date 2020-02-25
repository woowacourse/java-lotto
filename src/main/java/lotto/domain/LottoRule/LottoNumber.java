package lotto.domain.LottoRule;

public class LottoNumber implements Comparable<LottoNumber> {
    private Integer value;

    public LottoNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber otherNumber) {
        if (this.value > otherNumber.value)
            return 1;
        if (this.value == otherNumber.value)
            return 0;
        return -1;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
