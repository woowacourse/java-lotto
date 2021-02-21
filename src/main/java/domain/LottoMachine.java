package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoMachine {

    private final static int MIN_LOTTO_NUMBER = 1;
    private final static int MAX_LOTTO_NUMBER = 45;

    private final Price price;

    private LottoMachine(final Price price) {
        this.price = price;
    }

    public static LottoMachine valueOf(final Price price) {
        return new LottoMachine(price);
    }

    public List<LottoTicket> generateLottoTickets() {
        final int lottoTicketQuantity = price.getNumberOfTickets();
        final List<Integer> numberBox = generateNumberBox();

        return Stream
            .generate(() -> LottoTicket.valueOf(RandomLottoNumberGenerator.generate(numberBox)))
            .limit(lottoTicketQuantity)
            .collect(Collectors.toList());
    }

    private List<Integer> generateNumberBox() {
        return IntStream
            .rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    }
}
