package lotto.model;

import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class ManualTicket implements LottoGenerator {
    @Override
    public List<LottoTicket> generate(TicketNumber ticketNumber) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketNumber.getManualTicket(); i++) {
            lottoTickets.add(new LottoTicket(InputView.inputLottoTicket()));
        }
        return lottoTickets;
    }
}
