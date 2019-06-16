package lotto.db.dao;

import lotto.domain.WinningLotto;
import lotto.domain.dto.LottoGameResultDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Test
    void 당첨로또_삽입_테스트() throws SQLException {
        WinningLotto winningLotto = WinningLotto.of("7,11,12,13,15,16", 1);
        LottoGameDAO.addWinningLottoTicket(winningLotto);
    }

    @Test
    void 가장_최근에_삽입된_당첨로또를_리턴하는지_테스트_당첨번호() throws SQLException {
        WinningLotto winningLotto = WinningLotto.of("21,22,23,24,25,26", 27);
        LottoGameDAO.addWinningLottoTicket(winningLotto);

        List<String> actual = Arrays.asList(LottoGameDAO.findLatestWinningLotto().getWinningNumbers().split(","));
        Collections.sort(actual);

        assertEquals(Arrays.asList("21", "22", "23", "24", "25", "26"), actual);
    }

    @Test
    void 가장_최근에_삽입된_당첨로또를_리턴하는지_테스트_보너스볼() throws SQLException {
        WinningLotto winningLotto = WinningLotto.of("21,22,23,24,25,26", 27);
        LottoGameDAO.addWinningLottoTicket(winningLotto);

        int actual = LottoGameDAO.findLatestWinningLotto().getBonusBall();

        assertEquals(27, actual);
    }
}