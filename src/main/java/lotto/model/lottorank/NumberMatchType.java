package lotto.model.lottorank;

import java.util.Arrays;

public enum NumberMatchType {
    ZERO(0),
    THREE(3),
    FOUR(4),
    FIVE(5),
    FIVE_WITH_BONUS_NUMBER(5),
    SIX(6);

    private int countOfMatch;

    NumberMatchType(int countOfMatch) {
        this.countOfMatch = countOfMatch;
    }

    public static NumberMatchType findNumberMatchType(int countOfMatch, boolean matchBonusNumber) {
        if (Integer.valueOf(countOfMatch).equals(FIVE.countOfMatch)) {
            return matchBonusNumber ? FIVE_WITH_BONUS_NUMBER : FIVE;
        }
        return Arrays.stream(NumberMatchType.values())
                .filter(numberMatchType -> numberMatchType.matchCount(countOfMatch))
                .findFirst()
                .orElse(NumberMatchType.ZERO);
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(countOfMatch + "개 일치");
        if (this.equals(FIVE_WITH_BONUS_NUMBER)) {
            stringBuilder.append(", 보너스 볼 일치");
        }

        return stringBuilder.toString();
    }
}
