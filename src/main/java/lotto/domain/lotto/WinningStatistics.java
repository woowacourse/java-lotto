package lotto.domain.lotto;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;

public class WinningStatistics {

    private final Ranks ranks;
    private final double yield;

    public WinningStatistics(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        this.ranks = new Ranks(lottoTicket.unwrap().stream()
            .map(lottoNumbers -> getRank(winningNumbers, lottoNumbers))
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Function.identity(), Collectors.counting()),
                this::fillUnrankedCount)));

        this.yield = (double) ranks.getTotalWinnings() / lottoTicket.getTotalLottoPrice();
    }

    private Rank getRank(WinningNumbers winningNumbers, LottoNumbers lottoNumbers) {
        return Rank.getRank(
            winningNumbers.getMatchCount(lottoNumbers),
            winningNumbers.hasBonusNumber(lottoNumbers)
        );
    }

    private Map<Rank, Long> fillUnrankedCount(Map<Rank, Long> statistics) {
        Rank.getAllPossibleRanks().stream()
            .filter(rank -> !statistics.containsKey(rank))
            .forEach(rank -> statistics.put(rank, 0L));

        return statistics;
    }

    public Map<Rank, Long> unwrap() {
        return ranks.unwrap();
    }

    public double getYield() {
        return yield;
    }
}