package lotto.domain;

public class MatchCount {

    private static final int MINIMUM_MATCH_COUNT = 1;
    private static final String INVALID_MATCH_COUNT_EXCEPTION_MESSAGE = "유효하지 않는 일치 갯수입니다.";

    private final int matchCount;

    public MatchCount(int matchCount) {
        if (matchCount < MINIMUM_MATCH_COUNT || matchCount > Lotto.BALL_COUNT) {
            throw new IllegalArgumentException(INVALID_MATCH_COUNT_EXCEPTION_MESSAGE);
        }
        this.matchCount = matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
