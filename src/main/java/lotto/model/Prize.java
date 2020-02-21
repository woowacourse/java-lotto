package lotto.model;

import java.util.HashMap;

public class Prize {
    private static double prize = 0;

    public static double sumPrize(HashMap<String, Integer> resultCount) {
        prize += LottoResult.FIRST_GRADE.prizeResult(resultCount.get(LottoResult.FIRST_GRADE.getGrade()));
        prize += LottoResult.SECOND_GRADE.prizeResult(resultCount.get(LottoResult.SECOND_GRADE.getGrade()));
        prize += LottoResult.THIRD_GRADE.prizeResult(resultCount.get(LottoResult.THIRD_GRADE.getGrade()));
        prize += LottoResult.FOURTH_GRADE.prizeResult(resultCount.get(LottoResult.FOURTH_GRADE.getGrade()));
        prize += LottoResult.FIFTH_GRADE.prizeResult(resultCount.get(LottoResult.FIFTH_GRADE.getGrade()));
        return prize;
    }

    public static double getPrize() {
        return prize;
    }
}
