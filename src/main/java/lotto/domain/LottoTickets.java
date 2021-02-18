package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets generateAutomatic(int lottoTicketCounts, LottoNumberGenerator lottoNumberGenerator) {
        List<LottoTicket> lottoTickets = Stream.generate(() -> LottoTicket.from(lottoNumberGenerator.generate()))
                .limit(lottoTicketCounts)
                .collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    public LottoStatistics getStatistics(WinningLottoTicket winningLottoTicket) {
        Map<LottoRank, Long> statistics = lottoTickets.stream()
                .map(winningLottoTicket::compareNumbers)
                .collect(Collectors.groupingBy(lottoRank -> lottoRank, () -> new EnumMap<>(LottoRank.class),
                        Collectors.counting()));
        return new LottoStatistics(statistics);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
