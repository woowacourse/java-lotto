package domain;

import java.util.HashSet;
import java.util.Set;

public class WinningNumber {
    private Lotto winningNumbers;
    private LottoNumber bonusNumber;

    public WinningNumber(String[] numbers, String bonusNumber) {
        Set<LottoNumber> winningNumbers = new HashSet<>();
        for (String number : numbers) {
            winningNumbers.add(LottoNumber.valueOf(number));
        }
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
        checkDuplicatedLottoNumber();
    }

    public int countWinningMatch(Lotto myLotto) {
        checkLottoNull(myLotto);
        return winningNumbers.countMatchNumbers(myLotto);
    }

    public boolean isBonusMatch(Lotto myLotto) {
        checkLottoNull(myLotto);
        return myLotto.contains(bonusNumber);
    }

    private void checkLottoNull(Lotto myLotto) {
        if (myLotto == null) {
            throw new NullPointerException("비교할 로또가 없습니다.");
        }
    }

    private void checkDuplicatedLottoNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }
}