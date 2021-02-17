package lotto.domain;

public enum WinningResult {
    THREE(3, 5000, "3개 일치"),
    FOUR(4, 50000, "4개 일치"),
    FIVE(5, 1500000, "5개 일치"),
    FIVE_BONUS(5, 30000000, "5개 일치, 보너스 볼 일치"),
    SIX(6, 2000000000, "6개 일치");

    private int hitCount;
    private int reward;
    private String message;

    WinningResult(int hitCount, int reward, String message) {
        this.hitCount = hitCount;
        this.reward = reward;
        this.message = message;
    }

    @Override
    public String toString() {
        return message + " (" + reward + ")원 -";
    }
}
