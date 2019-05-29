package lotto.model;

public enum LottoRank {
        FIRST(6, 2_000_000_000),
        SECOND(5, 1_500_000),
        THIRD(4, 50_000),
        FOURTH(3, 5_000);

        private int matchNumber;
        private int prizes;

        LottoRank(int matchNumber, int prizes) {
                this.matchNumber = matchNumber;
                this.prizes = prizes;
        }

        public static LottoRank getLottoRank(int matchNumber) {
                LottoRank matchLottoRank = null;
                for (LottoRank lottoRank : values()) {
                        matchLottoRank = lottoRank.matchNumber == matchNumber ? lottoRank : matchLottoRank;
                }
                return matchLottoRank;
        }

        public static int getPrizes(int matchNumber) {
                int prizes = 0;
                for (LottoRank lottoRank : values()) {
                        prizes = lottoRank.matchNumber == matchNumber ? lottoRank.prizes : prizes;
                }
                return prizes;
        }

        public int getPrizes() {
                return prizes;
        }
}
