package lotto.domain.lottomanager;

import lotto.domain.WinningLotto;
import lotto.utils.NullCheckUtil;

import java.util.List;

public class BonusBall {
    private static final String ERROR_OVERLAPPED_WINNING_NUMBERS = "당첨 번호에 동일한 수가 존재합니다.";

    private LottoNumber bonusBall;

    public BonusBall(LottoNumber bonusBall, WinningLotto winningLotto) {
        checkOverlapWithWinningNumbers(bonusBall, winningLotto);
        this.bonusBall = bonusBall;
    }

    private void checkOverlapWithWinningNumbers(LottoNumber bonusBall, WinningLotto winningLotto) {
        if (winningLotto.isContainedWinningNumbers(bonusBall)) {
            throw new IllegalArgumentException(ERROR_OVERLAPPED_WINNING_NUMBERS);
        }
    }

    public static BonusBall createBonusBall(Integer bonusBall, WinningLotto winningLotto) {
        NullCheckUtil.checkNullInteger(bonusBall);
        return new BonusBall(LottoNumber.createLottoNumber(bonusBall), winningLotto);
    }

    public Boolean isContainNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusBall);
    }
}
