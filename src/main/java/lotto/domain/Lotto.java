package lotto.domain;

import java.util.*;

class Lotto {
    static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    private Lotto(final List<LottoNumber> numbers) {
        final Optional<List<LottoNumber>> optNumbers = Optional.ofNullable(numbers);
        validate(optNumbers.orElseThrow(IllegalArgumentException::new));

        this.numbers = new ArrayList<>(optNumbers.orElseThrow(() ->
                new LottoCreateArgumentException("입력이 null입니다"))
        );
    }

    private void validate(final List<LottoNumber> numbers) {
        checkValidSize(numbers);
        checkDuplication(numbers);
    }

    private void checkValidSize(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoCreateArgumentException(LOTTO_SIZE + "개 초과 생성");
        }
    }

    private void checkDuplication(final List<LottoNumber> numbers) {
        final Set<LottoNumber> semiParameter = new HashSet<>(numbers);
        if (numbers.size() != semiParameter.size()) {
            throw new LottoCreateArgumentException("중복된 숫자로 로또 생성");
        }
    }

    static Lotto of(final List<LottoNumber> numbers) {
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
}
