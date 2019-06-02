package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsFactory {
    public static List<LottoTicket> create(String[] customLottoInputs) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (String customLottoInput : customLottoInputs) {
            lottoTickets.add(LottoTicketFactory.create(customLottoInput));
        }

        return lottoTickets;
    }
}
