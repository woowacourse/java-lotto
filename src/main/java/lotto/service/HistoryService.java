package lotto.service;

import lotto.database.dao.LottoTicketDAO;
import lotto.database.dao.ResultDAO;
import lotto.database.dao.WinningLottoDAO;
import lotto.dto.HistoryDTO;

import java.sql.SQLException;

public class HistoryService {
    private static ResultDAO resultDAO = ResultDAO.getInstance();
    private static LottoTicketDAO lottoTicketDAO = LottoTicketDAO.getInstance();
    private static WinningLottoDAO winningLottoDAO = WinningLottoDAO.getInstance();

    public static HistoryDTO createHistoryDTO(int round) throws SQLException {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setPrize(resultDAO.getPrizeByRound(round));
        historyDTO.setWinningRate(resultDAO.getWinningRateByRound(round));
        historyDTO.setLottoNumbers(lottoTicketDAO.getLottoNumbersByRound(round));
        historyDTO.setWinningNumbers(winningLottoDAO.getWinnigNumbersByRound(round));
        return historyDTO;
    }
}
