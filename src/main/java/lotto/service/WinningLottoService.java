package lotto.service;

import lotto.dao.WinningLottoDAO;
import lottogame.domain.Lotto;
import lottogame.domain.ManualLottoGenerator;
import lottogame.domain.WinningLotto;

import java.util.List;
import java.util.Map;

public class WinningLottoService {
    public static void insertWinningLotto(int round, WinningLotto winningLotto) {
        List<Integer> lottoNumbers = winningLotto.getWinningLotto().getNumbers();
        int bonusNumber = winningLotto.getBonusNumber().getLottoNumber();
        WinningLottoDAO.getInstance().insertWinningLotto(round, lottoNumbers, bonusNumber);
    }

    public static WinningLotto findWinningLottoByRound(int round) {
        Map<String,String> winningLotto = WinningLottoDAO.getInstance().findWinningLottoByRound(round);
        Lotto lotto = ManualLottoGenerator.create(winningLotto.get("winningLotto"));
        return WinningLotto.generate(lotto, Integer.parseInt(winningLotto.get("bonusNumber")));
    }
}
