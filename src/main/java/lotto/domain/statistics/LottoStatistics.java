package lotto.domain.statistics;

import lotto.domain.primitive.Money;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;

public class LottoStatistics {
    private final RatingCounter ratingCounter;

    public LottoStatistics(final RatingCounter ratingCounter) {
        this.ratingCounter = ratingCounter;
    }

    public RatingCounter getRatingCounter() {
        return ratingCounter;
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
