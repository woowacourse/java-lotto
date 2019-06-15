package lotto.db.dao;

import lotto.db.dto.LottoGameResultDTO;
import lotto.domain.Factory.LottoTicketFactory;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoDAOTest {
    @Test
    void DB에서_당첨로또_갖고오는지_테스트() throws SQLException {
        LottoGameResultDTO winningLotto = WinningLottoDAO.findByWinningLottoId("1");

        assertEquals(winningLotto, WinningLottoDAO.findByWinningLottoId("1"));
    }

    @Test
    void 없는칼럼_요청할때_익셉션_테스트() throws SQLException {
        LottoGameResultDTO winningLotto = WinningLottoDAO.findByWinningLottoId("0");
        assertNull(winningLotto);
    }

    @Test
    void 당첨로또_삽입_테스트() throws SQLException {
        WinningLotto winningLotto = WinningLotto.of("7,11,12,13,15,16", 1);
        WinningLottoDAO.addWinningLottoTicket(winningLotto);
    }
}