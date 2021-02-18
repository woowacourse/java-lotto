package lotto.domain.rating;

import java.util.EnumMap;
import java.util.Map;

public class RatingInfo {

    private Map<Rating, Counter> ratings;

    public RatingInfo() {
        ratings = new EnumMap<>(Rating.class);
        for (Rating rating : Rating.values()) {
            ratings.put(rating, new Counter());
        }
    }

    public void update(final Rating rating) {
        Counter currentCounter = ratings.get(rating);
        ratings.put(rating, currentCounter.plus());
    }

    public int get(final Rating rating) {
        Counter counter = ratings.get(rating);
        return counter.getCount();
    }
}
