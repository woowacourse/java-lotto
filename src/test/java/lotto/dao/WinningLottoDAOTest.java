package lotto.dao;

import lotto.dto.WinningLottoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDAO;
    
    @BeforeEach
    void setup() {
        winningLottoDAO = new WinningLottoDAO();
    }

    @Test
    void DB에서_당첨로또_갖고오는지_테스트() throws SQLException {
        WinningLottoDTO winningLotto = winningLottoDAO.findByWinningLottoId("1");

        assertEquals(winningLotto, winningLottoDAO.findByWinningLottoId("1"));
    }

    @Test
    void 없는칼럼_요청할때_익셉션_테스트() throws SQLException {
        WinningLottoDTO winningLotto = winningLottoDAO.findByWinningLottoId("0");
        assertNull(winningLotto);
    }

    @Test
    void 당첨로또_삽입_테스트() throws SQLException {
        WinningLottoDTO winningLotto = new WinningLottoDTO(7,11,12,13,14,15,16);
        winningLottoDAO.addWinningLotto(winningLotto);
    }
}