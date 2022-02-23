import java.util.List;

public class WinningNumber {
    private static final String DUPLICATE_BONUS_BALL_MESSAGE = "[ERROR] 보너스 볼은 당첨 번호와 중복될수 없습니다.";
    List<Integer> winningNumbers;

    public WinningNumber(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int size() {
        return winningNumbers.size();
    }

    public void checkBonusBall(int bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_BALL_MESSAGE);
        }
    }
}
