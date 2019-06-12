package lotto.service;

import lotto.dao.RoundDAO;
import lotto.dao.WinningLottoDAO;
import lotto.domain.*;
import lotto.dto.LottosDTO;
import lotto.dto.WinningLottoDTO;
import lotto.dto.WinningResultDTO;
import lotto.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class WinningLottoService {
    private static final String DELIMITER = ",";

    private final Logger logger = LoggerFactory.getLogger(WinningLottoService.class);
    private final WinningLottoDAO winningLottoDAO = new WinningLottoDAO();

    public WinningLottoDTO.Create createWinningLotto(String winningLottoNumbers, String bonus) throws SQLException {
        logger.info("create winning lotto!");
        logger.info("input winning lotto numbers : " + winningLottoNumbers);
        List<String> winLotto = StringUtil.convertToList(winningLottoNumbers, DELIMITER);
        logger.info("splited list : " + winLotto.toString());
        int bonusNumber = Integer.parseInt(bonus);
        Lotto winningLotto = LottoFactory.createLottoManually(winLotto);

        winningLottoDAO.addWinningLotto(winningLotto, bonusNumber);

        return new WinningLottoDTO.Create(winningLotto, bonusNumber);
    }

    public WinningResultDTO.Create calculateResult(int round) throws SQLException {
        Lotto lotto = winningLottoDAO.findWinningLottoByRound(round);
        int bonusNumber = winningLottoDAO.findBonusNumberByRound(round);
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.get(bonusNumber));
        LottosDTO.Create lottos = new LottoService().retrieveLottos(round);
        WinningResult winningResult = new Lottos(lottos.getLottos()).match(winningLotto);
        return new WinningResultDTO.Create(
                winningResult.getResult(),
                winningResult.calculateRevenueRate(new RoundDAO().findAmountByRound(round)));
    }
}
