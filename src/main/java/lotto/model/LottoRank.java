package lotto.model;

public enum LottoRank {
        FIRST(6, 2_000_000_000),
        SECOND(5, 30_000_000),
        THIRD(5, 1_500_000),
        FOURTH(4, 50_000),
        FIFTH(3, 5_000);

        private int matchNumber;
        private int prizes;

        LottoRank(int matchNumber, int prizes) {
                this.matchNumber = matchNumber;
                this.prizes = prizes;
        }

        public static LottoRank getLottoRank(int matchNumber, boolean hasBonusBall) {
                if(matchNumber == 5 && hasBonusBall){
                        return SECOND;
                }
                LottoRank matchLottoRank = null;
                for (LottoRank lottoRank : values()) {
                        matchLottoRank = (lottoRank != SECOND && lottoRank.matchNumber == matchNumber) ? lottoRank : matchLottoRank;
                }
                return matchLottoRank;
        }

        public static int getPrizes(int matchNumber, boolean hasBonusBall) {
                if(matchNumber == 5 && hasBonusBall){
                        return SECOND.getPrizes();
                }
                int prizes = 0;
                for (LottoRank lottoRank : values()) {
                        prizes = (lottoRank != SECOND && lottoRank.matchNumber == matchNumber) ? lottoRank.prizes : prizes;
                }
                return prizes;
        }

        public int getPrizes() {
                return prizes;
        }
}
