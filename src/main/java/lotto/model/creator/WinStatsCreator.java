package lotto.model.creator;

import lotto.model.object.Lotto;
import lotto.model.LottoRank;
import lotto.model.object.WinStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WinStatsCreator {
        public static WinStats create(List<Lotto> purchasedLottos, Lotto winnerLotto) {
                Map<LottoRank, Integer> mappingStats = new HashMap<>();
                {
                        mappingStats.put(LottoRank.FIRST, 0);
                        mappingStats.put(LottoRank.SECOND, 0);
                        mappingStats.put(LottoRank.THIRD, 0);
                        mappingStats.put(LottoRank.FOURTH, 0);
                }
                return new WinStats(createMappingStats(purchasedLottos, winnerLotto, mappingStats));
        }

        private static Map<LottoRank, Integer> createMappingStats(List<Lotto> purchasedLottos, Lotto winnerLotto, Map<LottoRank, Integer> mappingStats) {
                for (Lotto purchaseLotto : purchasedLottos) {
                        int matchNumber = purchaseLotto.getMatchNumber(winnerLotto);
                        LottoRank lottoRank = LottoRank.getLottoRank(matchNumber);
                        mappingStats.computeIfPresent(lottoRank, (LottoRank key, Integer value) -> ++value);
                }
                return mappingStats;
        }
}
