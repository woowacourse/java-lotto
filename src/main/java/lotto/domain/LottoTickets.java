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

    public static LottoTickets generate(ManualTickets manualTickets, int automaticTicketCounts, LottoNumberGenerator lottoNumberGenerator) {
        Stream<LottoTicket> manualLottoTickets = generateManual(manualTickets);
        Stream<LottoTicket> automaticLottoTickets = generateAutomatic(automaticTicketCounts, lottoNumberGenerator);
        List<LottoTicket> concatLottoTickets = Stream.concat(manualLottoTickets, automaticLottoTickets)
                .collect(Collectors.toList());
        return new LottoTickets(concatLottoTickets);
    }

    private static Stream<LottoTicket> generateManual(ManualTickets manualTickets) {
        return manualTickets.getManualTickets()
                .stream()
                .map(ManualTicket::getManualTicketNumbers)
                .map(LottoTicket::from);
    }

    private static Stream<LottoTicket> generateAutomatic(int automaticTicketCounts, LottoNumberGenerator lottoNumberGenerator) {
        return Stream.generate(() -> LottoTicket.from(lottoNumberGenerator.generate()))
                .limit(automaticTicketCounts);
    }

    public LottoResult checkResult(WinningLottoTicket winningLottoTicket) {
        Map<LottoRank, Long> statistics = lottoTickets.stream()
                .map(winningLottoTicket::compareNumbers)
                .collect(Collectors.groupingBy(lottoRank -> lottoRank, () -> new EnumMap<>(LottoRank.class),
                        Collectors.counting()));
        return new LottoResult(statistics);
    }

    public int getTicketCounts() {
        return lottoTickets.size();
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
