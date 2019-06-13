package lotto.service;

import lotto.dao.WinningLottoDAO;
import lottogame.domain.WinningLotto;

import java.util.List;

public class WinningLottoService {
    public static void insertWinningLotto(int round, WinningLotto winningLotto) {
        List<Integer> lottoNumbers = winningLotto.getWinningLotto().getNumbers();
        int bonusNumber = winningLotto.getBonusNumber().getLottoNumber();
        WinningLottoDAO.getInstance().insertWinningLotto(round, lottoNumbers, bonusNumber);
    }
}
