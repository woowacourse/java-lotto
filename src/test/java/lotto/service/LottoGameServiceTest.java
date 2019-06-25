package lotto.service;

import lotto.db.dao.LottoGameDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoGameServiceTest {
    @Test
    void 가장_최근에_삽입된_당첨로또를_리턴하는지_테스트_당첨번호() throws SQLException {
        LottoGameService.addWinningLottoTicket("21,22,23,24,25,26", "27");

        List<String> actual = Arrays.asList(LottoGameDAO.findLatestWinningLotto().getWinningNumbers().split(","));
        Collections.sort(actual);

        assertEquals(Arrays.asList("21", "22", "23", "24", "25", "26"), actual);
    }

    @Test
    void 가장_최근에_삽입된_당첨로또를_리턴하는지_테스트_보너스볼() throws SQLException {
        LottoGameService.addWinningLottoTicket("21,22,23,24,25,26", "27");

        int actual = LottoGameDAO.findLatestWinningLotto().getBonusBall();

        assertEquals(27, actual);
    }
}