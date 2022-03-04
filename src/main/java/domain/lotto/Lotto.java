package domain.lotto;

import exception.lotto.LottoNumDuplicatedException;
import exception.lotto.LottoNumWrongSizeException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private static final int SIZE = 6;
    private final List<LottoNumber> value;

    private Lotto(List<LottoNumber> value) {
        validate(value);
        Collections.sort(value);
        this.value = value;
    }

    public static Lotto from(List<LottoNumber> value) {
        return new Lotto(value);
    }

    private static void validate(List<LottoNumber> value) {
        if (new HashSet<>(value).size() != value.size()) {
            throw new LottoNumDuplicatedException();
        }
        if (value.size() != SIZE) {
            throw new LottoNumWrongSizeException();
        }
    }

    private static void validate1(List<LottoNumber> value) {
        HashSet<LottoNumber> compareNums = new HashSet<>(value);
        if (compareNums.size() != value.size()) {
            throw new LottoNumDuplicatedException();
        }
        if (value.size() != SIZE) {
            throw new LottoNumWrongSizeException();
        }
    }

    public int countSameNum(final WinningLotto winningLotto) {
        return (int) value.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return value.contains(lottoNumber);
    }

    public List<LottoNumber> get() {
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
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(value, lotto1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lotto=" + value +
                '}';
    }
}