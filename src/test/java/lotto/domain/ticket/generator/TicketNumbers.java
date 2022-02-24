package lotto.domain.ticket.generator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TicketNumbers {

    private final List<Integer> numbers;

    public TicketNumbers(final int... numbers) {
        this.numbers = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
