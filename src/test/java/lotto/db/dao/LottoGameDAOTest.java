package lotto.db.dao;

import lotto.domain.dto.LottoGameResultDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LottoGameDAOTest {
    @Test
    void DB에서_당첨로또_갖고오는지_테스트() throws SQLException {
        LottoGameResultDTO winningLotto = LottoGameDAO.findByWinningLottoId("1");

        assertEquals(winningLotto, LottoGameDAO.findByWinningLottoId("1"));
    }

    @Test
    void 없는칼럼_요청할때_익셉션_테스트() throws SQLException {
        LottoGameResultDTO winningLotto = LottoGameDAO.findByWinningLottoId("0");
        assertNull(winningLotto);
    }
}