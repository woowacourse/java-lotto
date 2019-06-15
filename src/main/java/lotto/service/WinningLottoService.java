package lotto.service;

import lotto.dao.RoundDAO;
import lotto.dao.WinningLottoDAO;
import lotto.domain.*;
import lotto.dto.LottosDTO;
import lotto.dto.WinningLottoDTO;
import lotto.dto.WinningResultDTO;
import lotto.util.StringUtil;

import java.util.List;

public class WinningLottoService {
    private static final String DELIMITER = ",";

    private final WinningLottoDAO winningLottoDAO = new WinningLottoDAO();

    public WinningLottoDTO.Create createWinningLotto(String winningLottoNumbers, String bonus) {
        List<String> winLotto = StringUtil.convertToList(winningLottoNumbers, DELIMITER);
        int bonusNumber = Integer.parseInt(bonus);
        Lotto winningLotto = LottoFactory.createLottoManually(winLotto);

        winningLottoDAO.addWinningLotto(winningLotto, bonusNumber);

        return new WinningLottoDTO.Create(winningLotto, bonusNumber);
    }

    public WinningResultDTO.Create calculateResult(int round) {
        Lotto lotto = winningLottoDAO.findWinningLottoByRound(round);
        int bonusNumber = winningLottoDAO.findBonusNumberByRound(round);
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.get(bonusNumber));
        LottosDTO.Create lottos = new LottoService().findLottosByRound(round);
        WinningResult winningResult = new Lottos(lottos.getLottos()).match(winningLotto);
        return new WinningResultDTO.Create(
                winningResult.getResult(),
                winningResult.calculateRevenueRate(new RoundDAO().findAmountByRound(round)));
    }
}
