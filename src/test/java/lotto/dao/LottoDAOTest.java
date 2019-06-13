package lotto.dao;

import lotto.dto.LottoDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LottoDAOTest {
    private LottoDAO lottoDAO = new LottoDAO();

    @Test
    void DB에서_당첨로또_갖고오는지_테스트() throws SQLException {
        LottoDTO lotto = lottoDAO.findByLottoId("1");

        assertEquals(lotto, lottoDAO.findByLottoId("1"));
    }

    @Test
    void 없는칼럼_요청할때_익셉션_테스트() throws SQLException {
        LottoDTO lotto = lottoDAO.findByLottoId("0");
        assertNull(lotto);
    }

    @Test
    void 일반로또_삽입_테스트() throws SQLException {
        LottoDTO lotto = new LottoDTO(11, 12, 13, 14, 15, 16);
        lottoDAO.addLottoTicket(lotto);
    }

}