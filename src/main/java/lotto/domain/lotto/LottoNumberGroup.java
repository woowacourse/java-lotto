package lotto.domain.lotto;

import lotto.domain.lotto.LottoStrategy.LottoStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumberGroup implements Iterable<LottoNumber> {
    public static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> numbers;

    private LottoNumberGroup(List<LottoNumber> numbers) {
        this.numbers = Collections.unmodifiableSet(new TreeSet<>(numbers));
        checkDuplicate(numbers);
        checkLottoSize();
    }

    public static LottoNumberGroup create(LottoStrategy strategy) {
        try {
            return new LottoNumberGroup(
                    strategy.generate().stream()
                    .map(LottoNumber::of)
                    .collect(Collectors.toList())
            );
        } catch (InvalidLottoNumberException e) {
            throw new InvalidLottoNumberGroupException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new InvalidLottoNumberGroupException("로또 번호는 숫자로 구성되어야합니다.");
        }
    }

    private void checkDuplicate(List<LottoNumber> numbers) {
        if (numbers.size() != this.numbers.size()) {
            throw new InvalidLottoNumberGroupException("로또 번호는 중복이 불가능합니다.");
        }
    }

    private void checkLottoSize() {
        if (this.numbers.size() != LOTTO_SIZE) {
            throw new InvalidLottoNumberGroupException("로또 번호는 6개의 숫자로 구성되어야합니다.");
        }
    }

    public int countOfMatch(LottoNumberGroup lottoNumbers) {
        return (int) lottoNumbers.numbers.stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumberGroup that = (LottoNumberGroup) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public Iterator<LottoNumber> iterator() {
        return numbers.iterator();
    }
}
