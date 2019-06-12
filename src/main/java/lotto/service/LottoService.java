package lotto.service;

import lotto.dao.LottoDAO;
import lotto.dto.LottoDTO;

import java.sql.SQLException;
import java.util.List;

public class LottoService {
    private LottoDAO lottoDAO;

    public LottoService() {
        lottoDAO = new LottoDAO();
    }

    public List<LottoDTO> searchRoundLottos(String round) throws SQLException {
        return lottoDAO.selectRoundLotto(round);
    }

    public void insertLottos(List<LottoDTO> lottoDTOs) throws SQLException {
        for (LottoDTO lottoDTO : lottoDTOs){
            lottoDAO.insertLotto(lottoDTO);
        }
    }
}
