package lotto.model.object;

import lotto.model.LottoRank;

public class Yield {
        private double rate;

        public Yield(final Payment payment, final WinStats winStats) {
                long totalRevenue = 0;
                for (LottoRank lottoRank : LottoRank.values()) {
                        totalRevenue += lottoRank.getPrizes() * winStats.getRankCount(lottoRank);
                }
                rate = (double)totalRevenue / payment.getAmount();
        }

        public double getRate() {
                return rate;
        }
}

