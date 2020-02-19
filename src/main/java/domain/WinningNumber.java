package domain;

import java.util.HashSet;
import java.util.Set;

public class WinningNumber {
    private Lotto winningNumbers;
    private LottoNumber bonusNumber;

    public WinningNumber(String[] numbers, String bonusNumber) {
        Set<LottoNumber> winningNumbers = new HashSet<>();
        for (String number : numbers) {
            number.trim();
            winningNumbers.add(new LottoNumber(number));
        }
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public LottoResult findRank(Lotto myLotto) {
        checkLottoNull(myLotto);
        return LottoResult.findResult(countWinningMatch(myLotto), isBonusMatch(myLotto));
    }

    private void checkLottoNull(Lotto myLotto) {
        if (myLotto == null) {
            throw new NullPointerException("비교할 로또가 없습니다.");
        }
    }

    private int countWinningMatch(Lotto myLotto) {
        checkLottoNull(myLotto);
        return winningNumbers.countMatchNumbers(myLotto);
    }

    private boolean isBonusMatch(Lotto myLotto) {
        checkLottoNull(myLotto);
        return myLotto.contains(bonusNumber);
    }


}

