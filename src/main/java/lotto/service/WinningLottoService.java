package lotto.service;

import lotto.dao.LottoDAO;
import lotto.dao.RoundDAO;
import lotto.dao.WinningLottoDAO;
import lotto.domain.*;
import lotto.dto.WinningLottoDTO;
import lotto.dto.WinningResultDTO;

import java.util.List;

public class WinningLottoService {
    private final WinningLottoDAO winningLottoDAO = WinningLottoDAO.getInstance();
    private final RoundDAO roundDAO = RoundDAO.getInstance();
    private final LottoDAO lottoDAO = LottoDAO.getInstance();

    private WinningLottoService() {}

    private static class WinningLottoServiceHolder {
        static final WinningLottoService WINNING_LOTTO_SERVICE = new WinningLottoService();
    }

    public static WinningLottoService getInstance() {
        return WinningLottoServiceHolder.WINNING_LOTTO_SERVICE;
    }

    public WinningLottoDTO.Create createWinningLotto(List<String> winningLottoNumbers, int bonusNumber) {
        Lotto winningLotto = LottoFactory.createLottoManually(winningLottoNumbers);

        winningLottoDAO.addWinningLotto(winningLotto, bonusNumber, roundDAO.findMaxRound());

        return new WinningLottoDTO.Create(winningLotto, bonusNumber);
    }

    public WinningResultDTO.Create calculateResult(int round) {
        Lotto lotto = winningLottoDAO.findWinningLottoByRound(round);
        int bonusNumber = winningLottoDAO.findBonusNumberByRound(round);
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.get(bonusNumber));
        Lottos lottos = new Lottos(lottoDAO.findLottosByRound(round));
        WinningResult winningResult = lottos.match(winningLotto);
        return new WinningResultDTO.Create(
                winningResult.getResult(),
                winningResult.calculateRevenueRate(roundDAO.findAmountByRound(round)));
    }
}
