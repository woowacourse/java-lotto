package lotto.model.dto;

import lotto.model.LottoRank;

import java.util.Map;

public class ResultDTO {
        Map<LottoRank, Integer> winningResult;
        int revenue;
        double yield;

        public ResultDTO() {
        }

        public ResultDTO(Map<LottoRank, Integer> winningResult, int revenue, double yield) {
                this.winningResult = winningResult;
                this.revenue = revenue;
                this.yield = yield;
        }

        public Map<LottoRank, Integer> getWinningResult() {
                return winningResult;
        }

        public void setWinningResult(Map<LottoRank, Integer> winningResult) {
                this.winningResult = winningResult;
        }

        public int getRevenue() {
                return revenue;
        }

        public void setRevenue(int revenue) {
                this.revenue = revenue;
        }

        public double getYield() {
                return yield;
        }

        public void setYield(double yield) {
                this.yield = yield;
        }
}
