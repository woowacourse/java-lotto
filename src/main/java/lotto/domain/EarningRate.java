package lotto.domain;

import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;

public class EarningRate {

    public double calculate(final RatingInfo ratingInfo, final long money) {
        return totalSum(ratingInfo) / money;
    }

    private double totalSum(final RatingInfo ratingInfo) {
        double sum = 0;
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            sum += rating.getReward() * ratingInfo.get(rating);
        }
        return sum;
    }
}
