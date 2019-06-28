package lotto.dao;

import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.dto.LottoDTO;
import lotto.dto.LottoResultDTO;
import lotto.dto.WinningLottoDTO;
import spark.Session;
import java.sql.SQLException;
import java.util.List;

public class GameDAO {
    public static void addAll(Session session) throws SQLException {
        int round = RoundDAO.getInstance().getRound();
        List<LottoDTO> lottoDtos = ((LottoTickets) session.attribute("lottoTickets")).toDTO(round);
        WinningLottoDTO winningLottoDto = ((WinningLotto) session.attribute("winningLotto")).toDTO(round);
        LottoResultDTO lottoResultDto = ((LottoResult) session.attribute("lottoResult")).toDTO(round);

        LottoDAO lottoDao = LottoDAO.getInstance();
        lottoDao.addLotto(lottoDtos);
        WinningLottoDAO winningLottoDao = WinningLottoDAO.getInstance();
        winningLottoDao.addWinningLotto(winningLottoDto);
        LottoResultDAO lottoResultDao = LottoResultDAO.getInstance();
        lottoResultDao.addLottoResult(lottoResultDto);
    }
}
