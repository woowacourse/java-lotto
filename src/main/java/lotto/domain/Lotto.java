package lotto.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
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
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수가 올바르지 않습니다.");
        }
    }

    static void validateNumbersScope(List<Integer> numbers) {
        numbers.forEach(Lotto::validateNumberScope);
    }

    static void validateNumberScope(Integer number) {
        if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException("잘못된 로또 번호입니다.");
        }
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
