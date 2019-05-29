package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private static final int NUMBER_OF_WINNING_NUMBER = 6;

    private List<LottoNumber> winningLotto = new ArrayList<>();

    public WinningLotto(List<Integer> winningLotto) {
        if (winningLotto.size() != NUMBER_OF_WINNING_NUMBER) {
            throw new InvalidLottoNumberException("6개의 숫자를 입력해주세요.");
        }
        for (Integer lottoNumber : winningLotto) {
            addWinningLottoNumber(lottoNumber);
        }
    }

    private void addWinningLottoNumber(Integer lottoNumber) {
        if (winningLotto.contains(LottoNumber.getLottoNumber(lottoNumber))) {
            throw new InvalidLottoNumberException("중복되는 숫자가 존재합니다.");
        }

        winningLotto.add(LottoNumber.getLottoNumber(lottoNumber));
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return winningLotto.contains(lottoNumber);
    }
}
