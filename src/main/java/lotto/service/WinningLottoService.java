package lotto.service;

import lotto.dao.WinningLottoDAO;
import lotto.dto.WinningLottoDTO;

import java.sql.SQLException;
import java.util.List;

public class WinningLottoService {
    private WinningLottoDAO winningLottoDAO;

    public WinningLottoService() {
        this.winningLottoDAO = new WinningLottoDAO();
    }

    public List<WinningLottoDTO> searchWinningLotto(String round) throws SQLException {
        return winningLottoDAO.selectWinningLotto(round);
    }

    public void insertWinningLotto(WinningLottoDTO winningLottoDTO) throws SQLException {
        winningLottoDAO.insertWinningLotto(winningLottoDTO);
    }
}