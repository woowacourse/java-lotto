package lotto.domain;

import lotto.dto.TicketNumbersDto;

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

    public static LottoTickets generateManual(List<TicketNumbersDto> manualTicketsNumbers) {
        List<LottoTicket> lottoTickets = manualTicketsNumbers.stream()
                .map(TicketNumbersDto::getTicketNumbers)
                .map(LottoTicket::from)
                .collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    public LottoResult checkResult(WinningLottoTicket winningLottoTicket) {
        Map<LottoRank, Long> statistics = lottoTickets.stream()
                .map(winningLottoTicket::compareNumbers)
                .collect(Collectors.groupingBy(lottoRank -> lottoRank, () -> new EnumMap<>(LottoRank.class),
                        Collectors.counting()));
        return new LottoResult(statistics);
    }

    public LottoTickets concat(LottoTickets targetLottoTickets) {
        List<LottoTicket> concatLottoTickets = Stream.concat(this.lottoTickets.stream(), targetLottoTickets.lottoTickets.stream())
                .collect(Collectors.toList());
        return new LottoTickets(concatLottoTickets);
    }

    public int getTicketCounts() {
        return lottoTickets.size();
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
