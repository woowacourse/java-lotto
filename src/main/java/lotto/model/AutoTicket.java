package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoTicket implements LottoGenerator {
    private static final int FIRST_INDEX = 0;
    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_LENGTH = 6;

    @Override
    public List<LottoTicket> generate(TicketNumber ticketNumber) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketNumber.getAutoTicket(); i++) {
            lottoTickets.add(createAutoTicket());
        }
        return lottoTickets;
    }

    public LottoTicket createAutoTicket() {
        List<LottoNumber> autoTicket = new ArrayList<>();
        for (int i = FIRST_LOTTO_NUMBER; i <= LAST_LOTTO_NUMBER; i++) {
            autoTicket.add(new LottoNumber(i));
        }
        Collections.shuffle(autoTicket);
        autoTicket = autoTicket.subList(FIRST_INDEX, LOTTO_NUMBER_LENGTH);
        LottoNumber.sortLottoNumber(autoTicket);
        LottoTicket lottoTicket = new LottoTicket(autoTicket);
        return lottoTicket;
    }
}
