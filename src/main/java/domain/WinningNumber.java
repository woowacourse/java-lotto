package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumber {

    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_DUPLICATE_NUMBER = "로또 번호는 중복되면 안 됩니다.";
    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호가 당첨 번호와 중복됩니다.";

    private Set<LottoNumber> winningNumbers;
    private LottoNumber bonus;

    public WinningNumber(List<Integer> numbers) {
        winningNumbers = new HashSet<>();
        for (Integer number : numbers) {
            winningNumbers.add(new LottoNumber(number));
        }
        checkDuplicateNumber(winningNumbers);
    }

    private void checkDuplicateNumber(final Set<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public void addBonusNumber(int number) {
        LottoNumber bonusNumber = new LottoNumber(number);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
        bonus = bonusNumber;
    }

    public Set<LottoNumber> getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonus() {
        return bonus;
    }

}
