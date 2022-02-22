public enum LottoRank {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000),
    NOTHING(0);

    private int prizeAmount;

    LottoRank(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public static LottoRank of(long matchCount, boolean bonusMatch) {
        checkMatchCount(matchCount);
        if (isSecondPrize(matchCount, bonusMatch)) {
            return LottoRank.SECOND;
        } else if (isThirdPrize(matchCount, bonusMatch)) {
            return LottoRank.THIRD;
        } else if (isFourthPrize(matchCount)) {
            return LottoRank.FOURTH;
        } else if (isFifthPrize(matchCount)) {
            return LottoRank.FIFTH;
        } else if (isNothingPrize(matchCount)) {
            return LottoRank.NOTHING;
        }
        return LottoRank.FIRST;
    }

    private static void checkMatchCount(long matchCount) {
        if (matchCount < 0) {
            throw new IllegalArgumentException("일치 갯수는 0이상 6이하이여야 합니다.");
        }
    }

    private static boolean isSecondPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 5 && bonusMatch;
    }

    private static boolean isThirdPrize(long matchCount, boolean bonusMatch) {
        return matchCount == 5 && !bonusMatch;
    }

    private static boolean isFourthPrize(long matchCount) {
        return matchCount == 4;
    }

    private static boolean isFifthPrize(long matchCount) {
        return matchCount == 3;
}

    private static boolean isNothingPrize(long matchCount) {
        return matchCount <= 2;
    }
}
