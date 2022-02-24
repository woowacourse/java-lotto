package lotto.domain.ticket.generator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.ticket.condition.BallNumberRange;
import lotto.domain.ticket.condition.TicketSize;

public class RandomTicketGenerator implements TicketGenerator {

    private static final int SUBLIST_INCLUSIVE_START_INDEX = 0;
    private static final int SUBLIST_EXCLUSIVE_END_INDEX =
            SUBLIST_INCLUSIVE_START_INDEX + TicketSize.DEFAULT_SIZE.getSize();

    private final List<Integer> numbers;

    public RandomTicketGenerator() {
        final int inclusive_start = BallNumberRange.getInclusiveRangeStart();
        final int exclusive_end = BallNumberRange.getExclusiveRangeEnd();
        this.numbers = IntStream.range(inclusive_start, exclusive_end)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> generate() {
        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = numbers.subList(SUBLIST_INCLUSIVE_START_INDEX, SUBLIST_EXCLUSIVE_END_INDEX);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

}
