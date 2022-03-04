package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.enumeration.Rank;
import lotto.domain.generator.LottoTicketGenerator;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int autoCount, List<LottoTicket> manualLottoTickets, LottoTicketGenerator lottoTicketGenerator) {
        this.lottoTickets = generateTickets(autoCount, manualLottoTickets, lottoTicketGenerator);
    }

    public int totalCount() {
        return lottoTickets.size();
    }

    public LottoResult determine(WinningNumbers winningNumbers) {
        Map<Rank, Integer> ranks = new HashMap<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningNumbers.compare(lottoTicket);
            ranks.put(rank, ranks.getOrDefault(rank, 0) + 1);
        }

        return new LottoResult(ranks);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    private List<LottoTicket> generateTickets(int autoCount, List<LottoTicket> manualLottoTickets, LottoTicketGenerator lottoTicketGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>(manualLottoTickets);

        for (int i = 0; i < autoCount; i++) {
            LottoTicket lottoTicket = new LottoTicket(lottoTicketGenerator);

            lottoTickets.add(lottoTicket);
        }

        return lottoTickets;
    }
}
