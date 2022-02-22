public enum LottoRank {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000);

    private int prizeAmount;

    LottoRank(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public static LottoRank of(long matchCount, boolean bonusMatch) {
        if (isSecondPrize(matchCount, bonusMatch)) {
            return LottoRank.SECOND;
        } else if (isThirdPrize(matchCount, bonusMatch)) {
            return LottoRank.THIRD;
        }
        return LottoRank.FIRST;
    }

    private static boolean isSecondPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 5 && bonusMatch;
    }

    private static boolean isThirdPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 5 && !bonusMatch;
    }
}
