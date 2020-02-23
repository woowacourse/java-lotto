package lotto.domain;

import lotto.exception.LottoTicketException;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int COUNT_FOR_SECOND_RANK = 5;
    private static final int LOTTO_SIZE = 6;
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
        if (hasWrongSize(numbers)) {
            throw new LottoTicketException("로또의 숫자는 6개여야 합니다.");
        }
    }

    private boolean hasWrongSize(List<LottoNumber> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private void validateNumbersDuplication(List<LottoNumber> numbers) {
        if (hasDuplicatedNumbers(numbers)) {
            throw new LottoTicketException("로또의 숫자는 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicatedNumbers(List<LottoNumber> numbers) {
        return getDistinctCount(numbers) != numbers.size();
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
        int count = (int) numbers.stream()
                .filter(winningLottoTicket.numbers::contains)
                .count();

        if (count == COUNT_FOR_SECOND_RANK && numbers.contains(bonusNumber)) {
            return Rank.SECOND;
        }

        return Rank.of(count);
    }
}