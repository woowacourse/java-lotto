package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LottoResult.java
 * 당첨 결과(구입한 모든 로또 티켓에 대한 등수 통계) 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
public class LottoResult {

    private final Map<Rank, Integer> resultStatisticsMap;

    public LottoResult(Map<Rank, Integer> resultStatisticsMap) {
        this.resultStatisticsMap = Collections.unmodifiableMap(resultStatisticsMap);
    }

    public Map<Rank, Integer> result() {
        return this.resultStatisticsMap;
    }

    public Money getReward() {
        long total = 0;
        for (Rank rank : resultStatisticsMap.keySet()) {
            long count = resultStatisticsMap.get(rank);
            total += count * rank.getReward().toLong();
        }
        return Money.from(total);
    }
}
