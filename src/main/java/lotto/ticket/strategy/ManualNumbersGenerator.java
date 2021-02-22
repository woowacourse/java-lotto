package lotto.ticket.strategy;

import lotto.ticket.Number;
import lotto.ticket.util.SplitNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManualNumbersGenerator implements NumbersGenerator {
    private final List<Number> ticket;

    public ManualNumbersGenerator(String numbers) {
        this.ticket = new ArrayList<>(splitNumbers(numbers));
    }

    private List<Number> splitNumbers(String numbers) {
        return SplitNumbers.splitNumberStream(numbers)
                .collect(Collectors.toList());
    }

    @Override
    public List<Number> generate() {
        return Collections.unmodifiableList(ticket);
    }
}
