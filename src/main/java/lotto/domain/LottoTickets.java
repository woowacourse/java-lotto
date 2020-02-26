package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private static final String DELIMITER = "\n";
    private List<LottoTicket> lottoTickets;

    LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public static LottoTickets createAutoAndAdd(Money money, List<LottoTicket> manualTickets) {
        List<LottoTicket> lottoTickets = new ArrayList<>(manualTickets);
        int autoTicketsQuantity = money.calculateTicketQuantity() - manualTickets.size();
        for (int i = 0; i < autoTicketsQuantity; i++) {
            lottoTickets.add(LottoTicket.create());
        }
        return new LottoTickets(lottoTickets);
    }

    public LottoResult match(WinningTicket winningTicket) {
        LottoResult lottoResult = new LottoResult();
        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningTicket.match(lottoTicket);
            lottoResult.updateResult(rank);
        }
        return lottoResult;
    }

    List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
            .map(LottoTicket::toString)
            .collect(Collectors.joining(DELIMITER));
    }
}
