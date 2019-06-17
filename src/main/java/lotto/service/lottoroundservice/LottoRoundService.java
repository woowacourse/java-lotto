package lotto.service.lottoroundservice;

import lotto.dao.lotto.LottoRoundDAO;
import lotto.dto.LottoRoundDTO;

import java.sql.SQLException;
import java.util.List;

public class LottoRoundService {
    public static int addLottoRound() throws SQLException {
        return LottoRoundDAO.getInstance().insertLottoRoundReturnsKey();
    }

    public static List<LottoRoundDTO> getLottoRoundAll() throws SQLException {
        return LottoRoundDAO.getInstance().selectLottoRoundAll();
    }
}
