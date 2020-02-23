package domain;

public class LottoCount {
    private static final int BONUS_BALL_MATCH_COUNT = 5;
    private static final int BONUS_BALL_VALUE = 10;

    private int ballCount;

    public LottoCount(int ballCount, boolean matchBonusBall) {
        this.ballCount = validateFiveWIthBonusBall(ballCount, matchBonusBall);
    }

    private int validateFiveWIthBonusBall(int correctCount, boolean matchBonusBall) {
        if (correctCount == BONUS_BALL_MATCH_COUNT && matchBonusBall) {
            correctCount += BONUS_BALL_VALUE;
        }
        return correctCount;
    }

    public boolean isCorrectCount(int maybeCorrectCount) {
        return maybeCorrectCount == ballCount;
    }

}
