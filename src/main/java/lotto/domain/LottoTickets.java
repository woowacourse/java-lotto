package lotto.domain;

import java.util.*;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets generateLottoTickets(int lottoTicketCounts, LottoNumberGenerator lottoNumberGenerator) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        for (int i = 0; i < lottoTicketCounts; i++) {
            LottoTicket lottoTicket = LottoTicket.generateTicket(lottoNumberGenerator.generate());
            lottoTicketGroup.add(lottoTicket);
        }
        return new LottoTickets(lottoTicketGroup);
    }

    public int size() {
        return lottoTickets.size();
    }
}
