package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                        .map(LottoNumber::new)
                        .toList();
        validateNumbers(lottoNumbers);
        this.numbers= getSortedNumbers(lottoNumbers);
    }

    public boolean contains(final LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private List<LottoNumber> getSortedNumbers(final List<LottoNumber> numbers) {
        List<LottoNumber> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(
                sortedNumbers,
                Comparator.comparingInt(LottoNumber::getLottoNumber)
        );
        return sortedNumbers;
    }

    private void validateNumbers(final List<LottoNumber> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE + "개의 고유한 번호를 입력해야 합니다.");
        }
    }
}
