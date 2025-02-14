package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.dto.MatchCountDto;
import lotto.dto.Profit;
import lotto.dto.WinningInform;

public class LottoStatistics {
    private final Map<MatchRank, Integer> rankCounts;

    private LottoStatistics(Map<MatchRank, Integer> rankCounts) {
        this.rankCounts = Collections.unmodifiableMap(rankCounts);
    }

    public static LottoStatistics from(Wallet wallet, WinningInform winningInform) {
        Map<MatchRank, Integer> tempCounts = new EnumMap<>(MatchRank.class);
        for (MatchRank rank : MatchRank.values()) {
            tempCounts.put(rank, 0);
        }

        List<MatchCountDto> matchResults = wallet.getMatchResults(
                winningInform.matchLotto(),
                winningInform.bonusNumber()
        );

        for (MatchCountDto matchCountDto : matchResults) {
            MatchRank rank = MatchRank.getMatchRank(
                    matchCountDto.matchCount(),
                    matchCountDto.isBonusMatched()
            );
            tempCounts.merge(rank, 1, Integer::sum);
        }

        return new LottoStatistics(tempCounts);
    }

    public int getCountOf(MatchRank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public Profit calculateProfit(Money spentMoney) {
        long totalPrize = calculateTotalPrize();
        return Profit.from(totalPrize, spentMoney.getMoney());
    }

    private long calculateTotalPrize() {
        long total = 0;
        for (Map.Entry<MatchRank, Integer> entry : rankCounts.entrySet()) {
            MatchRank rank = entry.getKey();
            int count = entry.getValue();
            total += rank.getMoney() * count;
        }
        return total;
    }
}