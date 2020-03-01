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

    public static LottoTickets createManualTickets(List<String[]> manualNumbersInput) {
        List<LottoTicket> manualTickets = new ArrayList<>();
        for (String[] manualNumber : manualNumbersInput) {
            manualTickets.add(new LottoTicket(manualNumber));
        }
        return new LottoTickets(manualTickets);
    }

    public static LottoTickets createAutoAndAdd(Money money, LottoTickets manualTickets) {
        List<LottoTicket> lottoTickets = new ArrayList<>(manualTickets.lottoTickets);
        int autoTicketsQuantity = money.calculateTicketQuantity() - manualTickets.getLottoTicketsSize();
        for (int i = 0; i < autoTicketsQuantity; i++) {
            lottoTickets.add(LottoTicket.create());
        }
        return new LottoTickets(lottoTickets);
    }

    public LottoResult getMatchedResult(WinningTicket winningTicket) {
        LottoResult lottoResult = new LottoResult();
        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningTicket.match(lottoTicket);
            lottoResult.updateResult(rank);
        }
        return lottoResult;
    }

    public int getLottoTicketsSize() {
        return lottoTickets.size();
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
            .map(LottoTicket::toString)
            .collect(Collectors.joining(DELIMITER));
    }
}
