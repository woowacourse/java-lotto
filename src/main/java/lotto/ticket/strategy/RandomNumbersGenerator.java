package lotto.ticket.strategy;

import lotto.ticket.Number;
import lotto.ticket.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomNumbersGenerator implements NumbersGenerator {

    @Override
    public List<Number> generate() {
        List<Number> numbers = new ArrayList<>(Number.values());
        Collections.shuffle(numbers);

        return numbers.stream()
                .limit(Ticket.NUMBER_COUNT)
                .sorted()
                .collect(Collectors.toList());
    }
}
