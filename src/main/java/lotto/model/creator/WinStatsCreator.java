package lotto.model.creator;

import lotto.model.object.Lotto;
import lotto.model.LottoRank;
import lotto.model.object.WinStats;
import lotto.model.object.WinningInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WinStatsCreator {
        public static WinStats create(List<Lotto> purchasedLottos, WinningInfo winningInfo) {
                Map<LottoRank, Integer> mappingStats = new HashMap<>();
                {
                        mappingStats.put(LottoRank.FIRST, 0);
                        mappingStats.put(LottoRank.SECOND, 0);
                        mappingStats.put(LottoRank.THIRD, 0);
                        mappingStats.put(LottoRank.FOURTH, 0);
                        mappingStats.put(LottoRank.FIFTH, 0);
                }
                return new WinStats(createMappingStats(purchasedLottos, winningInfo, mappingStats));
        }

        private static Map<LottoRank, Integer> createMappingStats(List<Lotto> purchasedLottos, WinningInfo winningInfo, Map<LottoRank, Integer> mappingStats) {
                for (Lotto purchaseLotto : purchasedLottos) {
                        int matchNumber = purchaseLotto.getMatchNumber(winningInfo.getWinningLotto());
                        boolean hasBonusBall = purchaseLotto.hasBonusBall(winningInfo.getBonusBall());
                        LottoRank lottoRank = LottoRank.getLottoRank(matchNumber, hasBonusBall);
                        mappingStats.computeIfPresent(lottoRank, (LottoRank key, Integer value) -> ++value);
                }
                return mappingStats;
        }
}
