package lotto.domain.machine;

import lotto.domain.Money;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.AutoLottoTicketFactory;
import lotto.domain.ticket.LottoTickets;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoMachine extends LottoMachine {
    private final AutoLottoTicketFactory lottoTicketFactory = new AutoLottoTicketFactory();

    public AutoLottoMachine(Money lottoPrice) {
        super(lottoPrice);
    }

    @Override
    public LottoTickets createTickets(int numberOfTickets, List<LottoNumbers> lottoNumbersBundle) {
        return IntStream.range(0, numberOfTickets)
                .mapToObj(i -> lottoTicketFactory.createLottoTicket())
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
    }
}
