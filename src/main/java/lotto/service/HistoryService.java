package lotto.service;

import lotto.database.JdbcConnector;
import lotto.database.dao.LottoTicketDAO;
import lotto.database.dao.ResultDAO;
import lotto.database.dao.WinningLottoDAO;
import lotto.dto.HistoryDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class HistoryService {
    public static HistoryDTO createHistoryDTO(int round) throws SQLException {
        HistoryDTO historyDTO = new HistoryDTO();
        Connection con = JdbcConnector.getConnection();

        historyDTO.setPrize(new ResultDAO(con).getPrizeByRound(round));
        historyDTO.setWinningRate(new ResultDAO(con).getWinningRateByRound(round));
        historyDTO.setLottoNumbers(new LottoTicketDAO(con).getLottoNumbersByRound(round));
        historyDTO.setWinningNumbers(new WinningLottoDAO(con).getWinnigNumbersByRound(round));
        return historyDTO;
    }
}
