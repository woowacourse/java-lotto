package lottogame.domain.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lottogame.domain.LottoGameResult;
import lottogame.domain.Rank;
import lottogame.domain.number.LottoWinningNumbers;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public LottoTickets joinLottoTickets(final LottoTickets lottoTickets) {
        return new LottoTickets(
            Stream.concat(this.lottoTickets.stream(), lottoTickets.lottoTickets.stream())
                .collect(Collectors.toList())
        );
    }

    public List<String> getLottoTickets() {
        List<String> ticketsList = new ArrayList<>();
        for (LottoTicket lottoTicket : this.lottoTickets) {
            ticketsList.add(lottoTicket.getLottoNumbers().toString());
        }
        return ticketsList;
    }

    public LottoGameResult getMatchingResult(final LottoWinningNumbers lottoWinningNumbers,
        final Map<Rank, Integer> matchingResults) {
        for (LottoTicket lottoTicket : this.lottoTickets) {
            Rank rank = getRank(lottoWinningNumbers, lottoTicket);
            matchingResults.put(rank, matchingResults.get(rank) + 1);
        }
        return new LottoGameResult(matchingResults);
    }

    private Rank getRank(LottoWinningNumbers lottoWinningNumbers, LottoTicket lottoTicket) {
        int matchCount = lottoWinningNumbers.countMatchedWinningNumber(lottoTicket);
        boolean hasBonus = lottoWinningNumbers.hasBonusNumber(lottoTicket);
        return Rank.of(matchCount, hasBonus);
    }
}
