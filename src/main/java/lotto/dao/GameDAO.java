package lotto.dao;

import lotto.dto.GameDTO;
import lotto.dto.LottoDTO;
import lotto.dto.LottoResultDTO;
import lotto.dto.WinningLottoDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GameDAO {
    private final Connection conn;

    public GameDAO(Connection conn) {
        this.conn = conn;
    }

    public void addAll(GameDTO gameDto) throws SQLException {
        int round = gameDto.getRound();
        List<LottoDTO> lottoDtos = gameDto.getLottoTickets().toDTO(round);
        WinningLottoDTO winningLottoDto = gameDto.getWinningLotto().toDTO(round);
        LottoResultDTO lottoResultDto = gameDto.getLottoResult().toDTO(round);

        LottoDAO lottoDao = new LottoDAO(conn);
        lottoDao.addLotto(lottoDtos);
        WinningLottoDAO winningLottoDao = new WinningLottoDAO(conn);
        winningLottoDao.addWinningLotto(winningLottoDto);
        LottoResultDAO lottoResultDao = new LottoResultDAO(conn);
        lottoResultDao.addLottoResult(lottoResultDto);
    }
}
