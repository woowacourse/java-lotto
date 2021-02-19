package lotto.domain.statistics;

import lotto.domain.primitive.Money;
import lotto.domain.rating.LottoResult;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;

public class LottoStatistics {

    private static final String ENTER = System.lineSeparator();
    private static final String START_PRINT_FORMAT = "%d개 일치";
    private static final String HAS_BONUS_BALL = ", 보너스 볼 일치";
    private static final String END_PRINT_FORMAT = " (%d원) - %d개" + ENTER;
    private final RatingCounter ratingCounter;

    public LottoStatistics(final RatingCounter ratingCounter) {
        this.ratingCounter = ratingCounter;
    }

    public String getWinningDetail() {
        StringBuilder log = new StringBuilder();
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            log.append(ratingToString(rating, ratingCounter.get(rating)));
        }
        return log.toString();
    }

    public String ratingToString(final Rating rating, final int count) {
        LottoResult lottoResult = rating.getLottoResult();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(START_PRINT_FORMAT,lottoResult.getMatchedCount()));
        if (lottoResult.isSecond()) {
            stringBuilder.append(HAS_BONUS_BALL);
        }
        stringBuilder.append(String.format(END_PRINT_FORMAT,rating.getReward(),count));
        return stringBuilder.toString();
    }

    public double getEarningRate(final Money money) {
        return totalSum() / money.getValue();
    }

    public double totalSum() {
        double sum = 0;
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            sum += rating.getReward() * ratingCounter.get(rating);
        }
        return sum;
    }
}
