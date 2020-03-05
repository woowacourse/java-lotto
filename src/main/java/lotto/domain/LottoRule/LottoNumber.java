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
        return Integer.compare(this.value, otherNumber.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
