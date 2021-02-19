package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

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

    public LottoStatistics getStatistics(WinningLottoTicket winningLottoTicket) {
        Map<LottoRank, Long> statistics = lottoTickets.stream()
                .map(lottoTicket -> winningLottoTicket.compareNumbers(lottoTicket))
                .collect(Collectors.groupingBy(lottoRank -> lottoRank, Collectors.counting()));
        return new LottoStatistics(statistics);
    }

    public List<LottoTicket> getLottoTicket() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
