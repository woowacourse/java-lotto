package lotto.domain.statistics;

import lotto.domain.primitive.Money;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;

public class LottoStatistics {

    private static final String ENTER = System.lineSeparator();
    private static final String PRINT_FORMAT = "%d개 일치 (%d원) - %d개" + ENTER;
    private static final String SECOND_PRINT_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원) - %d개" + ENTER;
    private RatingInfo ratingInfo;
    private final StringBuilder log = new StringBuilder();

    public LottoStatistics(final RatingCounter ratingCounter) {
        this.ratingCounter = ratingCounter;
    }

    public String getWinningDetail() {
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            log.append(ratingToString(rating, ratingCounter.get(rating)));
        }
        return log.toString();
    }

    public String ratingToString(final Rating rating, final int count) {
        if (rating == Rating.SECOND) {
            return String
                    .format(SECOND_PRINT_FORMAT, rating.getMatchCount(), rating.getReward(), count);
        }
        return String
                .format(PRINT_FORMAT, rating.getMatchCount(), rating.getReward(), count);
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
