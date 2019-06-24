package lotto.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinStats {
        private Map<LottoRank, Integer> mappingStats;

        public WinStats(final List<Lotto> purchasedLottos, final WinningInfo winningInfo) {
                mappingStats = new LinkedHashMap<>();
                {
                        mappingStats.put(LottoRank.FIRST, 0);
                        mappingStats.put(LottoRank.SECOND, 0);
                        mappingStats.put(LottoRank.THIRD, 0);
                        mappingStats.put(LottoRank.FOURTH, 0);
                        mappingStats.put(LottoRank.FIFTH, 0);
                        mappingStats.put(LottoRank.NONE, 0);
                }
                createMappingStats(purchasedLottos, winningInfo);
        }

        private void createMappingStats(final List<Lotto> purchasedLottos, final WinningInfo winningInfo) {
                for (Lotto purchasedLotto : purchasedLottos) {
                        LottoRank lottoRank = winningInfo.getMatchRank(purchasedLotto);
                        mappingStats.computeIfPresent(lottoRank, (LottoRank key, Integer value) -> ++value);
                }
        }

        public int getRankCount(final LottoRank lottoRank) {
                return mappingStats.get(lottoRank);
        }

        public Map<LottoRank, Integer> getMappingStats() {
                return mappingStats;
        }
}
