package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoTickets = generateTickets(lottoCount, lottoNumberGenerator);
    }

    private List<LottoTicket> generateTickets(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            LottoTicket lottoTicket = new LottoTicket(lottoNumberGenerator);

            lottoTickets.add(lottoTicket);
        }

        return lottoTickets;
    }

    public int totalCount() {
        return lottoTickets.size();
    }

    public String getTicketsInfo() {
        String ticketsInfo = "";

        for (LottoTicket lottoTicket : lottoTickets) {
            ticketsInfo = ticketsInfo + lottoTicket.getLottoNumbers() + "\n";
        }

        return ticketsInfo;
    }

    public LottoResult determine(WinningNumbers winningNumbers) {
        Map<Rank, Integer> ranks = new HashMap<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningNumbers.compare(lottoTicket);
            ranks.put(rank, ranks.getOrDefault(rank, 0) + 1);
        }

        return new LottoResult(ranks);
    }
}
