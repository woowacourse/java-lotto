package lotto.dto;

import lotto.domain.LottoRank;

import java.util.Map;

public class WinningResultDTO {

    public static class Create {
        private final Map<LottoRank, Integer> result;
        private final double revenueRate;

        public Create(Map<LottoRank, Integer> result, double revenueRate) {
            this.result = result;
            this.revenueRate = revenueRate;
        }

        public Map<LottoRank, Integer> getResult() {
            return result;
        }

        public double getRevenueRate() {
            return revenueRate;
        }
    }
}
