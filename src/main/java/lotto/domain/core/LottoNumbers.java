package lotto.domain.core;

import lotto.domain.exceptions.LottoNumberException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers implements TicketNumbers {
    static final int NUMBER_COUNT = 6;
    private final List<TicketNumber> numbers;

    LottoNumbers(List<TicketNumber> numbers) {
        validate(numbers);
        this.numbers = Collections.unmodifiableList(numbers);
    }

    private void validate(List<TicketNumber> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new LottoNumberException();
        }
        if (new HashSet<>(numbers).size() != NUMBER_COUNT) {
            throw new LottoNumberException();
        }
    }

    @Override
    public List<TicketNumber> numbers() {
        return new ArrayList<>(numbers);
    }

    @Override
    public boolean contains(TicketNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
