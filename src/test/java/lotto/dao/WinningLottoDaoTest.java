package lotto.dao;


import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.dao.WinningLottoDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class WinningLottoDaoTest {

    @Test
    public void saveWinningLotto() throws SQLException {
        Lotto winningLottoTicket = new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16));
        LottoNumber lottoNumber = new LottoNumber(44);
        WinningLottoDao winningLottoDao = new WinningLottoDao();
        winningLottoDao.saveWinningLotto(winningLottoTicket, lottoNumber);

        int latesRound = winningLottoDao.getLatestRound();
        winningLottoDao.deleteWinningLotto(latesRound);
    }

    @Test
    public void fetchWinningLottto() throws SQLException {
        Lotto winningLottoTicket = new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16));
        LottoNumber bonusNumber = new LottoNumber(44);
        WinningLottoDao winningLottoDao = new WinningLottoDao();
        winningLottoDao.saveWinningLotto(winningLottoTicket, bonusNumber);

        int latestRound = winningLottoDao.getLatestRound();

        List<String> winnngLottoTest = Arrays.asList("11,12,13,14,15,16,보너스 번호 : 44");
        assertThat(winningLottoDao.fetchRequestWinningLotto(latestRound - 1)).isEqualTo(winnngLottoTest);

        winningLottoDao.deleteWinningLotto(latestRound);
    }


}
