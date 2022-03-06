package lotto.domain.lottonumber.vo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.util.constants.Lotto.LAST_LOTTO_NUMBER;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final String INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE = "로또 번호는 1 ~ 45 사이의 자연수여야합니다.";
    private static final List<LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = IntStream.range(0, LAST_LOTTO_NUMBER)
                .map(index -> index + 1)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private final int number;

    private LottoNumber(final int value) {
        this.number = value;
    }

    public static LottoNumber from(final int value) {
        return LOTTO_NUMBERS.stream()
                .filter(lottoNumber -> lottoNumber.number == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE));
    }

    public boolean hasSameNumberWith(final List<LottoNumber> others) {
        return others.stream()
                .anyMatch(other -> other.number == number);
    }

    public int getValue() {
        return this.number;
    }

    @Override
    public int compareTo(final LottoNumber another) {
        return this.number - another.number;
    }

    @Override
    public boolean equals(final Object o) {
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
