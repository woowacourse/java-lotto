package lotto;


import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.dao.WinningLottoDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;


public class WinningLottoDaoTest {
    @Test
    public void saveWinningLotto() throws SQLException {
        Lotto winningLottoTicket = new Lotto(Arrays.asList(11,12,13,14,15,16));
        LottoNumber lottoNumber = new LottoNumber(44);
        WinningLottoDao winningLottoDao = new WinningLottoDao();
        winningLottoDao.saveWinningLotto(winningLottoTicket, lottoNumber);
    }
}
