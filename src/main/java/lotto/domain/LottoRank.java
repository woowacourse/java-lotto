package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public enum LottoRank {
    NOTHING(0, 0),
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private final int correctNumber;
    private final int prizeAmount;

    LottoRank(final int correctNumber, final int prizeAmount) {
        this.correctNumber = correctNumber;
        this.prizeAmount = prizeAmount;
    }

    public static Map<LottoRank, Integer> initLottoRankMap() {
        Map<LottoRank, Integer> lottoRankMap = new LinkedHashMap<>();
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> lottoRankMap.put(lottoRank, 0));
        return lottoRankMap;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public static LottoRank valueOf(int sameCount, boolean bonus) {
        if (sameCount == SECOND.getCorrectNumber()) {
            return checkSecondOrThird(bonus);
        }
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.getCorrectNumber() == sameCount)
                .findFirst()
                .orElse(NOTHING);
    }

    private static LottoRank checkSecondOrThird(boolean bonus) {
        if (bonus) {
            return SECOND;
        }
        return THIRD;
    }
}
