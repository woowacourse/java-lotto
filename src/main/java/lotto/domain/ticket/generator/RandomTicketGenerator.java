package lotto.domain.ticket.generator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.Ticket;
import lotto.utils.BallNumberRange;
import lotto.utils.TicketSize;

public class RandomTicketGenerator implements TicketGenerator {

    private static final int SUBLIST_INCLUSIVE_START_INDEX = 0;
    private static final int SUBLIST_EXCLUSIVE_END_INDEX =
            SUBLIST_INCLUSIVE_START_INDEX + TicketSize.DEFAULT_SIZE.getSize();

    private final List<Integer> numbers;

    public RandomTicketGenerator() {
        this.numbers = BallNumberRange.getBallNumbers()
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public Ticket generateTicket() {
        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = numbers.subList(SUBLIST_INCLUSIVE_START_INDEX, SUBLIST_EXCLUSIVE_END_INDEX);
        return new Ticket(lottoNumbers);
    }

}
