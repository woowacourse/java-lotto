package lotto.domain.machine;

import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.AutoLottoTicketFactoryAdapter;
import lotto.domain.ticket.LottoTickets;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoMachine extends LottoMachine {
    private final AutoLottoTicketFactoryAdapter lottoTicketFactory = new AutoLottoTicketFactoryAdapter();

    public AutoLottoMachine(int lottoPrice) {
        super(lottoPrice);
    }

    @Override
    public LottoTickets createTickets(int numberOfTickets, List<LottoNumbers> lottoNumbersBundle) {
        return IntStream.range(0, numberOfTickets)
                .mapToObj(i -> lottoTicketFactory.createLottoTicket())
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
    }
}
