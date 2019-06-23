package lotto.service.lottoroundservice;

import lotto.dao.lotto.LottoRoundDAO;
import lotto.dto.LottoRoundDTO;

import java.sql.SQLException;
import java.util.List;

public class LottoRoundService {
    private static class LottoRoundServiceLazyHolder {
        private static final LottoRoundService INSTANCE = new LottoRoundService();
    }

    public static LottoRoundService getInstance() {
        return LottoRoundServiceLazyHolder.INSTANCE;
    }

    public int addLottoRound() throws SQLException {
        return LottoRoundDAO.getInstance().insertLottoRoundReturnsKey();
    }

    public List<LottoRoundDTO> getLottoRoundAll() throws SQLException {
        return LottoRoundDAO.getInstance().selectLottoRoundAll();
    }
}
