package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {

    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호가 당첨 번호와 중복됩니다.";

    private List<LottoBall> winningNumbers;
    private LottoBall bonus;

    public WinningLotto(List<Integer> numbers) {
        winningNumbers = new ArrayList<>();
        numbers.forEach(number -> winningNumbers.add(new LottoBall(number)));
    }

    public void addBonusNumber(int number) {
        LottoBall bonusNumber = new LottoBall(number);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
        bonus = bonusNumber;
    }

    public List<LottoBall> getWinningNumbers() {
        return winningNumbers;
    }

    public LottoBall getBonus() {
        return bonus;
    }

}
