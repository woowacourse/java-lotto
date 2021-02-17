package lotto;

public class LottoStats {
    private static final String ENTER = System.lineSeparator();
    private static final String PRINT_FORMAT = "%d개 일치 (%d원) - %d개" + ENTER;
    private static final String SECOND_PRINT_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원) - %d개" + ENTER;
    private RatingInfo ratingInfo;
    private final StringBuilder log = new StringBuilder();

    public LottoStats(final RatingInfo ratingInfo) {
        this.ratingInfo = ratingInfo;
    }

    public String getWinningDetail() {
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            log.append(ratingToString(rating, ratingInfo.get(rating)));
        }
        return log.toString();
    }

    public String ratingToString(final Rating rating, int count) {
        if (rating == Rating.SECOND) {
            return String
                .format(SECOND_PRINT_FORMAT, rating.getMatchCount(), rating.getReward(), count);
        }
        return String
            .format(PRINT_FORMAT, rating.getMatchCount(), rating.getReward(), count);
    }

    public int getEarningRate(Money money) {
        return totalSum() / money.getValue();
    }

    public int totalSum() {
        int sum = 0;
        for (Rating rating : Rating.values()) {
            if (rating == Rating.MISS) {
                break;
            }
            sum += rating.getReward() * ratingInfo.get(rating);
        }
        return sum;
    }
}
