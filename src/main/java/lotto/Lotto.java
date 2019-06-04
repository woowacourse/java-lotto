package lotto;

import lotto.exception.InvalidLottoNumbersException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int NUMBER_OF_NUMBERS_IN_LOTTO = 6;

    private final Set<LottoNumber> numbers;

    public Lotto (List<LottoNumber> numbers) {
        Set<LottoNumber> numberSet = new HashSet<>(numbers);
        checkNumberOfNumbers(numberSet);
        this.numbers = numberSet;
    }

    private void checkNumberOfNumbers(Set<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_OF_NUMBERS_IN_LOTTO) {
            throw new InvalidLottoNumbersException(
                    String.format("숫자를 %d개 입력하셨습니다. %d개의 숫자를 입력해주세요", numbers.size(), NUMBER_OF_NUMBERS_IN_LOTTO));
       }
    }
}
