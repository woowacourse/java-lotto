package lottogame.domain;

import lottogame.lottogameexception.InvalidBonusLottoNumberException;
import lottogame.lottogameexception.InvalidLottoNumberException;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    private WinningLotto(Lotto lotto, int bonusNumber) {
        this.winningLotto = lotto;
        checkValidBonusNumber(bonusNumber);
        this.bonusNumber = LottoNumber.Of(bonusNumber);
    }

    private WinningLotto() {
        this.winningLotto = null;
        this.bonusNumber = null;
    }

    public static WinningLotto generate(Lotto lotto, String bonusNumber) {
        try {
            return new WinningLotto(lotto, Integer.parseInt(bonusNumber));
        } catch (NumberFormatException | InvalidLottoNumberException | InvalidBonusLottoNumberException e) {
            System.out.println(e.getMessage());
            return new WinningLotto();
        }
    }

    private void checkValidBonusNumber(int bonusNumber) {
        try {
            checkDuplicateBonusNumber(LottoNumber.Of(bonusNumber));
        } catch (InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
            throw new InvalidBonusLottoNumberException("보너스 번호를 다시 입력해 주세요.");
        }
    }

    private void checkDuplicateBonusNumber(LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new InvalidBonusLottoNumberException("당첨 번호와 중복되지 않는 숫자를 입력해 주세요.");
        }
    }

    boolean isContain(LottoNumber lottoNumber) {
        return winningLotto.contains(lottoNumber);
    }

    boolean isBonusBallMatch(LottoNumber lottoNumber) {
        return bonusNumber == lottoNumber;
    }

    public boolean isNotCreatedWell() {
        return bonusNumber == null;
    }
}
