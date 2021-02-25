package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator {

    public static List<Integer> generate(final int inclusiveStart, final int inclusiveFinish) {
        final List<Integer> numbers = IntStream
                .rangeClosed(inclusiveStart, inclusiveFinish)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);

        return numbers.stream()
                .limit(LottoTicket.LOTTO_TICKET_SIZE)
                .collect(Collectors.toList());
    }
}
