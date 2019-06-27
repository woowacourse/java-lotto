package lotto.model;

public class Yield {
        private double rate;
        private int totalRevenue;

        public Yield(final Payment payment, final WinStats winStats) {
                totalRevenue = 0;
                for (LottoRank lottoRank : LottoRank.values()) {
                        totalRevenue += lottoRank.getPrizes() * winStats.getRankCount(lottoRank);
                }
                rate = (double)totalRevenue / payment.getAmount();
        }

        public double getRate() {
                return rate;
        }

        public int getTotalRevenue() {
                return totalRevenue;
        }
}

