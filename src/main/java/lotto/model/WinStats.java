package lotto.model;

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
