package lotto.domain.winning;

import lotto.domain.lottomanager.LottoNumber;

import java.util.List;

public class BonusBall {
    private static final String ERROR_OVERLAPPED_WINNING_NUMBERS = "당첨 번호에 동일한 수가 존재합니다.";
    private static final String ERROR_NULL_WINNING_LOTTO = "createBonusBall() has Null";
    private static final String ERROR_NULL_LOTTO_NUMBER = "isContainNumbers() has Null";

    private LottoNumber bonusBall;

    private BonusBall(LottoNumber bonusBall, WinningLotto winningLotto) {
        checkOverlapWithWinningNumbers(bonusBall, winningLotto);
        this.bonusBall = bonusBall;
    }

    private void checkOverlapWithWinningNumbers(LottoNumber bonusBall, WinningLotto winningLotto) {
        if (winningLotto.isContainedWinningNumbers(bonusBall)) {
            throw new IllegalArgumentException(ERROR_OVERLAPPED_WINNING_NUMBERS);
        }
    }

    public static BonusBall createBonusBall(int bonusBall, WinningLotto winningLotto) {
        if (winningLotto == null) {
            throw new IllegalArgumentException(ERROR_NULL_WINNING_LOTTO);
        }

        return new BonusBall(LottoNumber.createLottoNumber(bonusBall), winningLotto);
    }

    public boolean isContainNumbers(List<LottoNumber> lottoNumbers) {
        lottoNumbers.forEach(this::checkNullLottoNumber);

        return lottoNumbers.contains(bonusBall);
    }

    private void checkNullLottoNumber(LottoNumber lottoNumber) {
        if (lottoNumber == null) {
            throw new IllegalArgumentException(ERROR_NULL_LOTTO_NUMBER);
        }
    }
}
