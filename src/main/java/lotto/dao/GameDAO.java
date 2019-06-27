package lotto.dao;

import lotto.dto.*;
import java.sql.SQLException;
import java.util.List;

public class GameDAO {
    public void addAll(GameDTO gameDto) throws SQLException {
        int round = gameDto.getRound();
        List<LottoDTO> lottoDtos = gameDto.getLottoTickets().toDTO(round);
        WinningLottoDTO winningLottoDto = gameDto.getWinningLotto().toDTO(round);
        LottoResultDTO lottoResultDto = gameDto.getLottoResult().toDTO(round);

        LottoDAO lottoDao = LottoDAO.getInstance();
        lottoDao.addLotto(lottoDtos);
        WinningLottoDAO winningLottoDao = WinningLottoDAO.getInstance();
        winningLottoDao.addWinningLotto(winningLottoDto);
        LottoResultDAO lottoResultDao = LottoResultDAO.getInstance();
        lottoResultDao.addLottoResult(lottoResultDto);
    }
}
