package lotto.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.LottoRank;
import lotto.domain.Money;

public class LottoStatisticsResponse {

    private final Map<LottoWinningResponse, Long> responseCountMap;
    private final int money;

    public LottoStatisticsResponse(List<LottoRank> ranks, Money money) {
        this.responseCountMap = createEmptyMap();
        mergeRanks(ranks);
        this.money = money.getAmount();
    }

    private Map<LottoWinningResponse, Long> createEmptyMap() {
        return Arrays.stream(LottoRank.values())
            .map(LottoWinningResponse::from)
            .collect(Collectors.toMap(rank -> rank, rank -> 0L));
    }

    private void mergeRanks(List<LottoRank> ranks) {
        ranks.stream()
            .map(LottoWinningResponse::from)
            .forEach((rank) -> responseCountMap.merge(rank, 1L, Long::sum));
    }

    public Map<LottoWinningResponse, Long> getResponseCountMap() {
        return responseCountMap;
    }

    public int getMoney() {
        return money;
    }
}
