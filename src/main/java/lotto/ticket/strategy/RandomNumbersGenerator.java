package lotto.ticket.strategy;

import lotto.ticket.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.ticket.Number.*;

public class RandomNumbersGenerator implements NumbersGenerator {

    @Override
    public List<Number> generate() {
        List<Number> numbers = new ArrayList<>(Number.values());
        Collections.shuffle(numbers);

        return numbers.stream()
                .limit(NUMBER_COUNT_IN_LOTTO)
                .sorted()
                .collect(Collectors.toList());
    }
}
