package lottogame.domain.lotto;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final Pattern BONUS_NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    private static final class LottoNumberCache {
        static final int LOW = 1;
        static final int HIGH = 45;
        static final LottoNumber[] CACHE;

        static {
            CACHE = IntStream.rangeClosed(LOW, HIGH)
                    .mapToObj(LottoNumber::new)
                    .toArray(LottoNumber[]::new);
        }
    }

    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber of(int number) {
        validate(number);
        return LottoNumberCache.CACHE[number - 1];
    }

    public static LottoNumber of(String number) {
        validate(number);
        return LottoNumber.of(Integer.parseInt(number));
    }

    private static void validate(String number) {
        if (!BONUS_NUMBER_PATTERN.matcher(number).matches()) {
            throw new IllegalArgumentException("로또 번호는 1과 45사이의 숫자여야 합니다.");
        }
    }

    private static void validate(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1과 45사이의 숫자여야 합니다.");
        }
    }

    public int value() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.lottoNumber - lottoNumber.lottoNumber;
    }
}
