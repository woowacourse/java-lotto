package lottogame.domain.lotto;

import lottogame.utils.InvalidLottoNumberRangeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoNumber {
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
    private final int number;

    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
    static {
        for (int i = LOTTO_NUMBER_MIN; i <= LOTTO_NUMBER_MAX; i++) {
            lottoNumbers.put(i, new LottoNumber(i));
        }
    }

    public LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        if (!lottoNumbers.containsKey(number)) {
            throw new InvalidLottoNumberRangeException();
        }
        return lottoNumbers.get(number);
    }

    public static List<LottoNumber> of(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> LottoNumber.valueOf(number))
                .collect(Collectors.toList());
    }

    public LottoNumber values() {
        return LottoNumber.valueOf(number);
    }

    public int getNumber() {
        return number;
    }

    public boolean equals(int number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
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
