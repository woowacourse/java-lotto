package lotto.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final String LOTTO_NUMBER_DUPLICATION_ERROR_MESSAGE = "중복된 로또 번호가 있습니다.";
    private static final String LOTTO_SIZE_ERROR_MESSAGE = "로또 번호의 개수가 올바르지 않습니다.";
    private static final String LOTTO_NUMBER_SCOPE_ERROR_MESSAGE = "범위(1 ~ 45)를 벗어난 로또 번호가 있습니다.";

    protected List<Integer> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateDuplication(numbers);
        validateSize(numbers);
        validateNumbersScope(numbers);
        this.lottoNumbers = numbers;
    }

    static void validateDuplication(List<Integer> numbers) {
        LinkedHashSet<Integer> duplicationNumbers = new LinkedHashSet<>(numbers);
        if (duplicationNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }

    static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR_MESSAGE);
        }
    }

    static void validateNumbersScope(List<Integer> numbers) {
        numbers.forEach(Lotto::validateNumberScope);
    }

    static void validateNumberScope(Integer number) {
        if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SCOPE_ERROR_MESSAGE);
        }
    }

    public int countCorrectNumber(WinningLotto winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto.getLottoNumbers()::contains)
                .count();
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
