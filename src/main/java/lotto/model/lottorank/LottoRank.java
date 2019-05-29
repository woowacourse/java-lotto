package lotto.model.lottorank;

import java.util.Arrays;

public enum LottoRank {
    MISS(NumberMatchType.ZERO, WinningMoneyType.MISS_WINNING_MONEY),
    FIFTH(NumberMatchType.THREE, WinningMoneyType.FIFTH_WINNING_MONEY),
    FOURTH(NumberMatchType.FOUR, WinningMoneyType.FOURTH_WINNING_MONEY),
    THIRD(NumberMatchType.FIVE, WinningMoneyType.THIRD_WINNING_MONEY),
    SECOND(NumberMatchType.FIVE_WITH_BONUS_NUMBER, WinningMoneyType.SECOND_WINNING_MONEY),
    FIRST(NumberMatchType.SIX, WinningMoneyType.FIRST_WINNING_MONEY);

    private NumberMatchType numberMatchType;
    private WinningMoneyType winningMoney;

    LottoRank(NumberMatchType numberMatchType, WinningMoneyType winningMoney) {
        this.numberMatchType = numberMatchType;
        this.winningMoney = winningMoney;
    }

    public int getWinningMoney() {
        return winningMoney.getWinningMoney();
    }

    public NumberMatchType getNumberMatchType() {
        return numberMatchType;
    }

    public static LottoRank findRank(int countOfMatch, boolean matchBonusNumber) {
        NumberMatchType numberMatchType = NumberMatchType.findNumberMatchType(countOfMatch, matchBonusNumber);

        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.matchNumberMatchType(numberMatchType))
                .findFirst()
                .orElse(LottoRank.MISS);
    }

    private boolean matchNumberMatchType(NumberMatchType numberMatchType) {
        return this.numberMatchType.equals(numberMatchType);
    }
}
