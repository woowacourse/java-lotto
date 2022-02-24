package lotto.domain.ticket.generator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomTicketGenerator implements TicketGenerator {

    private static final int RANDOM_RANGE_INCLUSIVE_START = 1;
    private static final int RANDOM_RANGE_EXCLUSIVE_END = 46;
    private static final int SUBLIST_INCLUSIVE_START_INDEX = 0;
    private static final int SUBLIST_EXCLUSIVE_END_INDEX = 6;

    private final List<Integer> numbers;

    public RandomTicketGenerator() {
        this.numbers = IntStream.range(RANDOM_RANGE_INCLUSIVE_START, RANDOM_RANGE_EXCLUSIVE_END)
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
