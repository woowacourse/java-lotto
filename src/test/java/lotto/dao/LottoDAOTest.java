package lotto.dao;

import lotto.dto.LottoDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LottoDAOTest {
    private LottoDAO lottoDAO = new LottoDAO();

    @Test
    void 일반로또_삽입_테스트() throws SQLException {
        LottoDTO lotto = new LottoDTO(11, 12, 13, 14, 15, 16);
        lottoDAO.addLottoTicket(lotto);
    }

}