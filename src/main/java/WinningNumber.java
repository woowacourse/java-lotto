import java.util.List;

public class WinningNumber {

    private static final String DUPLICATE_BONUS_BALL_MESSAGE = "[ERROR] 보너스 볼은 당첨 번호와 중복될수 없습니다.";
    private static final String WINNING_NUMBER_LENGTH_ONLY_SIX = "[ERROR] 당첨 번호는 6자리여야 합니다.";
    private static final String WINNING_NUMBER_DUPLICATE_MESSAGE = "[ERROR] 당첨 번호는 중복되지 않는 6개의 숫자여야 합니다.";

    private static final int LENGTH_STANDARD = 6;

    private final List<LottoNumber> winningNumbers;

    public WinningNumber(List<LottoNumber> winningNumbers) {
        checkWinningNumberLength(winningNumbers);
        checkDuplicatedWinningNumber(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    public void checkBonusBall(LottoNumber bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_BALL_MESSAGE);
        }
    }

    public boolean contains(LottoNumber number) {
        return winningNumbers.contains(number);
    }

    private void checkWinningNumberLength(List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != LENGTH_STANDARD) {
            throw new IllegalArgumentException(WINNING_NUMBER_LENGTH_ONLY_SIX);
        }
    }

    private void checkDuplicatedWinningNumber(List<LottoNumber> winningNumbers) {
        int duplicatedCount = (int) winningNumbers.stream().distinct().count();
        if (duplicatedCount != winningNumbers.size()) {
            throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE_MESSAGE);
        }
    }
}
