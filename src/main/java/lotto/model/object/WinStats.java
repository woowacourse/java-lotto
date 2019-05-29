package lotto.model.object;

import lotto.model.LottoRank;

import java.util.Map;

public class WinStats {
        private Map<LottoRank, Integer> mappingStats;

        public WinStats(Map<LottoRank, Integer> mappingStats) {
                this.mappingStats = mappingStats;
        }

        public Map<LottoRank, Integer> getMappingStats() {
                return mappingStats;
        }
}
