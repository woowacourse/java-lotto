package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    public static final int MIN = 1;
    public static final int MAX = 45;

    private static final List<LottoNumber> NUMBER_CACHE =
        IntStream.rangeClosed(MIN, MAX)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validate(number);
        return NUMBER_CACHE.stream()
            .filter(lottoNumber -> lottoNumber.number == number)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("입력받은 숫자에 해당하는 로또 번호가 없습니다."));
    }

    private static void validate(int number) {
        if (!isInLottoRange(number)) {
            throw new IllegalArgumentException(String.format("로또 숫자는 %d~%d 사이의 숫자여야 합니다.", MIN, MAX));
        }
    }

    private static boolean isInLottoRange(int number) {
        return number <= MAX && number >= MIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getNumber() {
        return number;
    }
}
