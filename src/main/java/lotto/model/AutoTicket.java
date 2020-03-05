package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoTicket implements LottoGenerator {
    private static final int FIRST_INDEX = 0;

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
        autoTicket = autoTicket.subList(FIRST_INDEX, LottoTicket.LOTTO_NUMBER_LENGTH);
        LottoNumber.sortLottoNumber(autoTicket);
        return new LottoTicket(autoTicket);
    }
}
