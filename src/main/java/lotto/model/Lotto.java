package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumberFactory;

public class Lotto {
    private static final String ERROR_SIZE = "[ERROR] 로또 번호는 6개여야 합니다";
    private static final String ERROR_DUPLICATE = "[ERROR] 로또 번호는 중복되면 안됩니다";
    private static final int NUMBERS_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        checkSize(numbers);
        checkDuplicate(numbers);
    }

    private void checkSize(List<LottoNumber> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException(ERROR_SIZE);
        }
    }

    private void checkDuplicate(List<LottoNumber> numbers) {
        if (getDistinctCount(numbers) != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    private int getDistinctCount(List<LottoNumber> numbers) {
        return (int) numbers.stream()
                .distinct()
                .count();
    }

    public static Lotto ofRandom() {
        return new Lotto(LottoNumberFactory.getRandomNumbers(NUMBERS_SIZE));
    }

    public static Lotto from(List<String> rawNumbers) {
        List<LottoNumber> numbers = rawNumbers.stream()
                .map(LottoNumberFactory::getNumber)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public boolean contains(LottoNumber number) {
        return this.numbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(number));
    }

    public int match(Lotto comparingLotto) {
        return (int) this.numbers.stream()
                .filter(comparingLotto::contains)
                .count();
    }

    public List<Integer> getValues() {
        return this.numbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
    }
}
