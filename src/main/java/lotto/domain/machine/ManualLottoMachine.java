package lotto.domain.machine;

import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTicketFactory;
import lotto.domain.ticket.LottoTickets;

import java.util.List;

public class ManualLottoMachine extends LottoMachine {
    private final LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();

    public ManualLottoMachine(int lottoPrice) {
        super(lottoPrice);
    }

    @Override
    public LottoTickets createTickets(int numberOfTickets, List<LottoNumbers> lottoNumbersBundle) {
        return null;
    }
}
