package lotto.domain.lotto;

import lotto.exception.InvalidLottoNumbersException;

import java.util.*;

public class Lotto {
    public static final int NUMBER_OF_NUMBERS_IN_LOTTO = 6;

    private final Set<LottoNumber> numbers;

    public Lotto (List<LottoNumber> numbers) {
        Set<LottoNumber> numberSet = new HashSet<>(numbers);
        checkNumberOfNumbers(numberSet);
        this.numbers = numberSet;
    }

    private void checkNumberOfNumbers(Set<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_OF_NUMBERS_IN_LOTTO) {
            throw new InvalidLottoNumbersException(
                    String.format("숫자를 %d개 입력하셨습니다. %d개의 숫자를 중복없이 입력해주세요",
                            numbers.size(), NUMBER_OF_NUMBERS_IN_LOTTO));
       }
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
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
}
