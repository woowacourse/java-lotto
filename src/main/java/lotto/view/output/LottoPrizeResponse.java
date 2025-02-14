package lotto.view.output;

import java.util.Objects;
import lotto.model.LottoPrize;

public class LottoPrizeResponse {

    private final int matchCount;
    private final boolean bonusNumberMatches;
    private final int prize;
    private final boolean isWin;

    private LottoPrizeResponse(int matchCount, boolean bonusNumberMatches, int prize, boolean isWin) {
        this.matchCount = matchCount;
        this.bonusNumberMatches = bonusNumberMatches;
        this.prize = prize;
        this.isWin = isWin;
    }

    public static LottoPrizeResponse from(LottoPrize lottoPrize) {
        return new LottoPrizeResponse(
                lottoPrize.getMatchCount(),
                lottoPrize.isBonusNumberMatches(),
                lottoPrize.getPrize(),
                lottoPrize.isWin()
        );
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusNumberMatches() {
        return bonusNumberMatches;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isWin() {
        return isWin;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoPrizeResponse that = (LottoPrizeResponse) o;
        return matchCount == that.matchCount && bonusNumberMatches == that.bonusNumberMatches && prize == that.prize
                && isWin == that.isWin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, bonusNumberMatches, prize, isWin);
    }
}
