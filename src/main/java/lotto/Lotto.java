package lotto;

import java.util.*;

class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        final Optional<List<Integer>> optNumbers = Optional.ofNullable(numbers);
        validate(optNumbers.orElseThrow(IllegalArgumentException::new));
        // TODO IllegalArgumentMessage
        this.numbers = new ArrayList<>(optNumbers.orElseThrow(() -> new IllegalArgumentException()));
    }

    private void validate(final List<Integer> numbers) {
        checkValidSize(numbers);
        checkDuplication(numbers);
    }

    private void checkValidSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            //TODO
            throw new IllegalArgumentException(LOTTO_SIZE + "개 초과 생성");
        }
    }

    private void checkDuplication(final List<Integer> numbers) {
        final Set<Integer> semiParameter = new HashSet<>(numbers);
        if (numbers.size() != semiParameter.size()) {
            // TODO 중복된 숫자에 관련된 Exception 추가
            throw new IllegalArgumentException("중복된 숫자로 로또 생성");
        }
    }

    static Lotto of(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    int matchCount(final Lotto another) {
        List<Integer> semiLotto = new ArrayList<>();
        semiLotto.addAll(this.numbers);
        semiLotto.addAll(another.numbers);

        Set<Integer> semiSet = new HashSet<>(semiLotto);
        return LOTTO_SIZE - (semiSet.size() - LOTTO_SIZE);
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
