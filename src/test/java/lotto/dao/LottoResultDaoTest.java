package lotto.dao;

import lotto.WebUILottoApplication;
import lotto.model.*;
import lotto.model.dao.LottoResultDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LottoResultDaoTest {
    @Test
    public void saveResultTest() throws SQLException {
        Money money = new Money("5000");
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoList.add(new Lotto(Arrays.asList(3, 4, 5, 6, 7, 8)));
        lottoList.add(new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16)));
        lottoList.add(new Lotto(Arrays.asList(5, 6, 7, 8, 9, 10)));
        Lottos lottos = new Lottos(lottoList);

        Lotto winningLottoTicket = new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16));
        LottoNumber bonusNumber = new LottoNumber(8);

        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);
        LottoResult lottoResult = new LottoResult(money, lottos.getPrizes(winningLotto));

        LottoResultDao dao = new LottoResultDao();
        dao.saveResult(WebUILottoApplication.makeReadableLottoResult(lottoResult), money, lottoResult);
    }
}
