package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
        this.numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateNotDuplicate(numbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호 개수는 6개여야 합니다.");
        }
    }

    private void validateNotDuplicate(List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public List<LottoNumber> getMatchedLottoNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
            .filter(numbers::contains)
            .collect(Collectors.toList());
    }
}
