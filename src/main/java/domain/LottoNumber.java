package domain;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final Map<Integer, LottoNumber> CACHE =
            IntStream.rangeClosed(
                            LottoRule.MIN_LOTTO_NUMBER.getValue(),
                            LottoRule.MAX_LOTTO_NUMBER.getValue())
                    .boxed()
                    .collect(Collectors.toMap(
                            key -> key,
                            LottoNumber::new));

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber from(int number) {
        if (CACHE.containsKey(number)) {
            return CACHE.get(number);
        }

        String exceptionMessage = String.format("로또 번호는 %d ~ %d 사이여야 합니다.",
                LottoRule.MIN_LOTTO_NUMBER.getValue(),
                LottoRule.MAX_LOTTO_NUMBER.getValue());
        throw new IllegalArgumentException(exceptionMessage + "입력된 값: " + number);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LottoNumber) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
