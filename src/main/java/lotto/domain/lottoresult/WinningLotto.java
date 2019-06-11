package lotto.domain.lottoresult;

import lotto.domain.lotto.*;
import lotto.domain.lotto.LottoStrategy.ManualLottoStrategy;

public class WinningLotto {
    private final LottoNumberGroup winningNumbers;
    private final LottoNumber bonusNumber;

    private WinningLotto(LottoNumberGroup winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validateBonusNumber();
    }

    public static WinningLotto create(String winningNumbersText, String bonusNumberText) {
        try {
            return new WinningLotto(
                    LottoNumberGroup.create(new ManualLottoStrategy(winningNumbersText)),
                    LottoNumber.of(bonusNumberText)
            );
        } catch (InvalidLottoNumberGroupException e) {
            throw new InvalidWinningLottoException(e.getMessage());
        } catch (InvalidLottoNumberException e) {
            throw new InvalidWinningLottoException(e.getMessage());
        }
    }

    private void validateBonusNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new InvalidWinningLottoException("당첨 번호에 포함된 번호입니다.");
        }
    }

    public LottoRank match(LottoTicket userLottoTicket) {
        return LottoRank.rankOf(
                userLottoTicket.countOfMatch(winningNumbers),
                userLottoTicket.contains(bonusNumber)
        );
    }
}
