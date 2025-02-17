package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int countMatchedNumber(Lotto lotto) {
        List<Integer> lottoNumbers = new ArrayList<>(lotto.getNumbers());
        lottoNumbers.retainAll(winningNumbers.getNumbers());
        return lottoNumbers.size();
    }

    public boolean isBonusNumberMatched(Lotto lotto) {
        return lotto.getNumbers()
                .stream()
                .anyMatch(number -> number == bonusNumber.getValue());
    }

    private void validate(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.getNumbers()
                .stream()
                .anyMatch(number -> number == bonusNumber.getValue())) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    public WinningResult calculateWinningResult(LottoWallet lottoWallet) {
        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : lottoWallet.getLottoWallet()) {
            final int matchedCount = countMatchedNumber(lotto);
            final boolean isBonusMatched = isBonusNumberMatched(lotto);
            WinningInfo winningInfo = WinningInfo.of(matchedCount, isBonusMatched);
            winningResult.increaseCount(winningInfo, 1);
        }
        return winningResult;
    }
}
