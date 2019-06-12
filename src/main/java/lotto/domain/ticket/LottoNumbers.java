package lotto.domain.ticket;

import lotto.domain.exceptions.LottoNumberException;

import java.util.*;

public class LottoNumbers implements TicketNumbers {
    static final int NUMBER_COUNT = 6;
    private final List<TicketNumber> numbers;

    LottoNumbers() {
        this(LottoNumberPool.random());
    }

    LottoNumbers(List<TicketNumber> numbers) {
        validate(numbers);
        this.numbers = Collections.unmodifiableList(numbers);
    }

    private void validate(List<TicketNumber> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new LottoNumberException();
        }
    }

    @Override
    public int matchNumber(TicketNumbers numbers) {
        Set<TicketNumber> ticketNumbers = new HashSet<>(this.numbers);
        ticketNumbers.addAll(numbers.numbers());
        return NUMBER_COUNT - ticketNumbers.size();
    }

    @Override
    public List<TicketNumber> numbers() {
        return new ArrayList<>(numbers);
    }

    @Override
    public boolean contains(TicketNumber number) {
        return numbers.contains(number);
    }
}
