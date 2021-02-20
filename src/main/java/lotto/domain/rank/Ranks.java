package lotto.domain.rank;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoNumbers;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningNumbers;

public class Ranks {

    private final Map<Rank, Long> ranks;

    private Ranks(Map<Rank, Long> ranks) {
        this.ranks = new HashMap<>(ranks);
    }

    public static Ranks valueOf(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        return new Ranks(lottoTicket.unwrap().stream()
            .map(lottoNumbers -> getRank(winningNumbers, lottoNumbers))
            .filter(rank -> rank != Rank.FAIL)
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Function.identity(), Collectors.counting()),
                Ranks::fillUnrankedCount)));
    }

    private static Rank getRank(WinningNumbers winningNumbers, LottoNumbers lottoNumbers) {
        return Rank.getRank(
            winningNumbers.getMatchCount(lottoNumbers),
            winningNumbers.hasBonusNumber(lottoNumbers)
        );
    }

    private static Map<Rank, Long> fillUnrankedCount(Map<Rank, Long> statistics) {
        Rank.getAllPossibleRanks().stream()
            .filter(rank -> !statistics.containsKey(rank))
            .forEach(rank -> statistics.put(rank, 0L));

        return statistics;
    }

    public Long getTotalWinnings() {
        return Rank.getAllPossibleRanks().stream()
            .mapToLong(rank -> rank.getWinnings() * ranks.getOrDefault(rank, 0L))
            .sum();
    }

    public Map<Rank, Long> unwrap() {
        return new HashMap<>(ranks);
    }
}
