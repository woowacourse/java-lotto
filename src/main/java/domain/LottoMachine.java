package domain;

import static domain.LottoTicket.LOTTO_MAX_NUMBER;
import static domain.LottoTicket.LOTTO_MIN_NUMBER;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public LottoTicket generateLottoTicket(LottoTicketGenerator generator) {
        return generator.generateLottoTicket(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);
    }

    public List<LottoTicket> generateLottoTickets(int lottoTicketNumber, LottoTicketGenerator generator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoTicketNumber; i++) {
            LottoTicket lottoTicket = generateLottoTicket(generator);
            lottoTickets.add(lottoTicket);
        }
        return lottoTickets;
    }
}
