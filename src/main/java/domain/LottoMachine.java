package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoMachine {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private LottoMachine() {
    }

    public static List<LottoTicket> generateLottoTickets(Price price) {
        final int lottoTicketQuantity = price.getNumberOfTickets();
        final List<Integer> numberBox = generateNumberBox();

        return Stream
            .generate(() -> LottoTicket.valueOf(RandomLottoNumberGenerator.generate(numberBox)))
            .limit(lottoTicketQuantity)
            .collect(Collectors.toList());
    }

    private static List<Integer> generateNumberBox() {
        return IntStream
            .rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    }
}
