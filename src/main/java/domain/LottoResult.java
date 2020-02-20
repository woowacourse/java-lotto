package domain;

public class LottoResult {

    private int correctCount;

    public LottoResult(int correctCount, boolean matchBonusBall) {
        this.correctCount = validateFiveWIthBonusBall(correctCount, matchBonusBall);
    }

    private int validateFiveWIthBonusBall(int correctCount, boolean matchBonusBall) {
        if(correctCount == 5 && matchBonusBall) {
            correctCount += 10;
        }
        return correctCount;
    }

    public boolean isCorrectCount(int maybeCorrectCount) {
        return maybeCorrectCount == correctCount;
    }

}
