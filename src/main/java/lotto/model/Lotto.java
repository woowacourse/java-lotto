package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumberCache;

/*
 * 로또 한 장을 의미하는 Class
 */
public class Lotto {
    private static final String ERROR_SIZE = "[ERROR] 로또 번호는 6개여야 합니다";
    private static final String ERROR_DUPLICATE = "[ERROR] 로또 번호는 중복되면 안됩니다";
    private static final int NUMBERS_SIZE = 6;

    private final List<LottoNumber> numbers;
    private final boolean auto;

    public Lotto(List<LottoNumber> numbers, boolean auto) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = List.copyOf(numbers);
        this.auto = auto;
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
        return new Lotto(LottoNumberCache.getRandomNumbers(NUMBERS_SIZE), true);
    }

    public static Lotto from(List<String> inputs) {
        List<LottoNumber> numbers = inputs.stream()
                .map(LottoNumberCache::getNumber)
                .collect(Collectors.toList());
        return new Lotto(numbers, false);
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

    public boolean isAuto() {
        return auto;
    }
}
