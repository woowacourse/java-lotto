package lotto.model;

import java.util.HashMap;

public class Prize {
    private double prize = 0;

    public double sumPrize(HashMap<String, Integer> resultCount) {
        this.prize += LottoResult.FIFTH_GRADE.prizeResult(resultCount.get(LottoResult.FIFTH_GRADE.getGrade()));
        this.prize += LottoResult.FOURTH_GRADE.prizeResult(resultCount.get(LottoResult.FOURTH_GRADE.getGrade()));
        this.prize += LottoResult.THIRD_GRADE.prizeResult(resultCount.get(LottoResult.THIRD_GRADE.getGrade()));
        this.prize += LottoResult.SECOND_GRADE.prizeResult(resultCount.get(LottoResult.SECOND_GRADE.getGrade()));
        this.prize += LottoResult.FIRST_GRADE.prizeResult(resultCount.get(LottoResult.FIRST_GRADE.getGrade()));
        return this.prize;
    }
}
