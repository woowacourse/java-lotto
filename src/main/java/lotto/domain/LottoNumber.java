package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record LottoNumber(
        int number
) implements Comparable<LottoNumber> {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    static {
        for (int i = MINIMUM; i <= MAXIMUM; i++) {
            CACHE.add(new LottoNumber(i));
        }
    }

    public LottoNumber {
        validate(number);
    }

    public static LottoNumber of(final int number) {
        LottoNumber lottoNumber = CACHE.get(number - 1);

        if (Objects.isNull(lottoNumber)) {
            lottoNumber = new LottoNumber(number);
        }
        return lottoNumber;
    }

    private void validate(int number) {
        if (number < MINIMUM || number > MAXIMUM) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d ~ %d 값을 입력해야 합니다.", MAXIMUM, MINIMUM));
        }
    }

    public static List<LottoNumber> values() {
        return CACHE;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number, o.number);
    }
}
