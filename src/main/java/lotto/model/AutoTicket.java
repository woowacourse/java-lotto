package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoTicket implements LottoGenerator {

    @Override
    public List<LottoTicket> generate(TicketInformation ticketInformation) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketInformation.getAutoTicketCount(); i++) {
            lottoTickets.add(createAutoTicket());
        }
        return lottoTickets;
    }

    public LottoTicket createAutoTicket() {
        List<LottoNumber> autoTicket = LottoNumber.allLottoNumbers();
        Collections.shuffle(autoTicket);
        return LottoTicket.makeAutoTicket(autoTicket);
    }
}
