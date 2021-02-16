package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoMachine implements LottoMachine {
    private final LottoTicketFactory lottoTicketFactory = new AutoLottoTicketFactory();

    @Override
    public List<LottoTicket> createTickets(int numberOfTickets) {
        return IntStream.range(0, numberOfTickets)
            .mapToObj(i -> lottoTicketFactory.createLottoTicket())
            .collect(Collectors.toList());
    }
}
