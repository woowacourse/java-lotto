package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumber {

    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호가 당첨 번호와 중복됩니다.";

    private List<LottoNumber> winningNumbers;
    private LottoNumber bonus;

    public WinningNumber(List<Integer> numbers) {
        winningNumbers = new ArrayList<>();
        numbers.forEach(number -> winningNumbers.add(new LottoNumber(number)));
    }

    public void addBonusNumber(int number) {
        LottoNumber bonusNumber = new LottoNumber(number);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
        bonus = bonusNumber;
    }

    public List<LottoNumber> getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}
