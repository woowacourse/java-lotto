package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private static final int NUMBER_OF_WINNING_NUMBER = 6;

    private List<LottoNumber> winningLotto = new ArrayList<>();
    private LottoNumber bonusNumber;

    public WinningLotto(List<Integer> winningLotto) {
        if (winningLotto.size() != NUMBER_OF_WINNING_NUMBER) {
            throw new InvalidLottoNumberException("6개의 숫자를 입력해주세요.");
        }
        for (Integer lottoNumber : winningLotto) {
            addWinningLottoNumber(lottoNumber);
        }
    }

    public void addBonusNumber(int bonusNumber) {
        if (!winningLotto.contains(LottoNumber.Of(bonusNumber))) {
            this.bonusNumber = LottoNumber.Of(bonusNumber);
            return;
        }
        throw new InvalidLottoNumberException("당첨 번호와 중복되지 않는 숫자를 입력해 주세요.");
    }

    private void addWinningLottoNumber(Integer lottoNumber) {
        if (winningLotto.contains(LottoNumber.Of(lottoNumber))) {
            throw new InvalidLottoNumberException("중복되는 숫자가 존재합니다.");
        }

        winningLotto.add(LottoNumber.Of(lottoNumber));
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return winningLotto.contains(lottoNumber);
    }

    public boolean isBonusBallMatch(LottoNumber lottoNumber) {
        return bonusNumber == lottoNumber;
    }
}
