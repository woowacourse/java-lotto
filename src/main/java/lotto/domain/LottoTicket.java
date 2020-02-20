package lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int COUNT_FOR_SECOND_RANK = 5;
    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    private void validate(List<LottoNumber> numbers) {
        validateNumbersCount(numbers);
        validateNumbersDuplication(numbers);
    }

    private void validateNumbersCount(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또의 숫자는 6개여야 합니다.");
        }
    }

    private void validateNumbersDuplication(List<LottoNumber> numbers) {
        if (getDistinctCount(numbers) != numbers.size()) {
            throw new IllegalArgumentException("로또의 숫자는 중복될 수 없습니다.");
        }
    }

    private long getDistinctCount(List<LottoNumber> numbers) {
        return numbers.stream()
                .distinct()
                .count();
    }

    public boolean contains(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public Rank compare(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        int count = (int) this.numbers.stream()
                .filter(winningLottoTicket.numbers::contains)
                .count();

        if (count == COUNT_FOR_SECOND_RANK && this.numbers.contains(bonusNumber)) {
            return Rank.SECOND;
        }

        return Rank.of(count);
    }
}