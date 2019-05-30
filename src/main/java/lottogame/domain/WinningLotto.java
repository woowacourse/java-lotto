package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;

import java.util.List;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningLotto(List<Integer> winningLotto) {
        this.winningLotto = ManualLottoGenerator.create(winningLotto);
        this.bonusNumber = null;
    }

    public void addBonusNumber(int bonusNumber) {
        if (!winningLotto.contains(LottoNumber.Of(bonusNumber))) {
            this.bonusNumber = LottoNumber.Of(bonusNumber);
            return;
        }
        throw new InvalidLottoNumberException("당첨 번호와 중복되지 않는 숫자를 입력해 주세요.");
    }

    boolean isContain(LottoNumber lottoNumber) {
        return winningLotto.contains(lottoNumber);
    }

    boolean isBonusBallMatch(LottoNumber lottoNumber) {
        return bonusNumber == lottoNumber;
    }

    public boolean isBonusNumberNull() {
        return bonusNumber == null;
    }
}
