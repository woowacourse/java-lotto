package lotto.domain;

import java.util.Objects;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(Set<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("중복된 숫자가 있거나 6자리가 아닙니다. 다시 확인해주세요.");
        }
        this.numbers = numbers;
    }

    public int countMatchedNumber(Lotto winningLotto) {
        int count = 0;
        for (LottoNumber number : numbers) {
            count += winningLotto.contain(number) ? 1 : 0;
        }
        return count;
    }

    public boolean contain(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (LottoNumber number : numbers) {
            sb.append(number);
            sb.append(", ");
        }
        return sb.toString().substring(0, sb.toString().length() - 2) + "]";
    }
}