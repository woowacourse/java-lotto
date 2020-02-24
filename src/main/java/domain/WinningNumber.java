package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumber {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumber(String[] numbers, String bonusNumber) {
        this.winningNumbers = LottoFactory.createOneManualLotto(numbers);
        checkNotNumber(bonusNumber);
        this.bonusNumber = AllLottoNumbers.get(Integer.parseInt(bonusNumber));
        checkDuplicatedLottoNumber();
    }

    public LottoResult countWinningLotto(final Lottos lottos) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos.getLottos()){
            LottoRank rank = LottoRank.findRank(countWinningMatch(lotto), isBonusMatch(lotto));
            lottoResult.addWinningRankCount(rank);
        }
        return lottoResult;
    }

    private int countWinningMatch(final Lotto myLotto) {
        checkLottoNull(myLotto);
        return winningNumbers.countMatchNumbers(myLotto);
    }

    private boolean isBonusMatch(final Lotto myLotto) {
        checkLottoNull(myLotto);
        return myLotto.contains(bonusNumber);
    }

    private void checkLottoNull(final Lotto myLotto) {
        if (myLotto == null) {
            throw new NullPointerException("비교할 로또가 없습니다.");
        }
    }

    private void checkDuplicatedLottoNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    private void checkNotNumber(final String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("보너스 넘버는 숫자여야 합니다. 입력한 문자 : %s", number));
        }
    }
}