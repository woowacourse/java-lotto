package lotto.domain;

import lotto.domain.exception.LottoCreateException;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    private Lotto(final List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(final List<LottoNumber> numbers) {
        checkValidSize(numbers);
        checkDuplication(numbers);
    }

    private void checkValidSize(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoCreateException(LOTTO_SIZE + "개를 입력해주세요");
        }
    }

    private void checkDuplication(final List<LottoNumber> numbers) {
        final Set<LottoNumber> semiParameter = new HashSet<>(numbers);
        if (numbers.size() != semiParameter.size()) {
            throw new LottoCreateException("중복된 숫자로 로또 생성");
        }
    }

    public static Lotto of(final List<LottoNumber> numbers) {
        return new Lotto(numbers);
    }

    int matchCount(final Lotto another) {
        List<LottoNumber> semiLotto = new ArrayList<>();
        semiLotto.addAll(this.numbers);
        semiLotto.addAll(another.numbers);

        Set<LottoNumber> semiSet = new HashSet<>(semiLotto);
        return LOTTO_SIZE - (semiSet.size() - LOTTO_SIZE);
    }

    boolean contains(LottoNumber other) {
        return this.numbers.contains(other);
    }

    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(this.numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lotto)) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
