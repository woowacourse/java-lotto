package domain;

public class LottoResult {

    public static final int BONUS_BALL_MATCH_COUNT = 5;
    public static final int BONUS_BALL_VALUE = 10;

    private int correctCount;

    public LottoResult(int correctCount, boolean matchBonusBall) {
        this.correctCount = validateFiveWIthBonusBall(correctCount, matchBonusBall);
    }

    private int validateFiveWIthBonusBall(int correctCount, boolean matchBonusBall) {
        if(correctCount == BONUS_BALL_MATCH_COUNT && matchBonusBall) {
            correctCount += BONUS_BALL_VALUE;
        }
        return correctCount;
    }

    public boolean isCorrectCount(int maybeCorrectCount) {
        return maybeCorrectCount == correctCount;
    }

}
