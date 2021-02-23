package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {

    private LottoMachine() {
    }

    public static List<LottoTicket> generateLottoTickets(Price price) {
        final int lottoTicketQuantity = price.getNumberOfTickets();

        return Stream
            .generate(() -> LottoTicket.valueOf(RandomLottoNumberGenerator.generate()))
            .limit(lottoTicketQuantity)
            .collect(Collectors.toList());
    }
}
