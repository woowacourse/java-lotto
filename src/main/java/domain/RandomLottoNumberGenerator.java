package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final List<Integer> numberBox = IntStream
        .rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
        .boxed()
        .collect(Collectors.toList());

    public static List<Integer> generate() {
        Collections.shuffle(numberBox);

        return numberBox.stream()
            .limit(LottoTicket.LOTTO_TICKET_SIZE)
            .collect(Collectors.toList());
    }
}
