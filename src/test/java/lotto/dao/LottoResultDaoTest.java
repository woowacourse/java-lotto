package lotto.dao;

import lotto.WebUILottoApplication;
import lotto.model.*;
import lotto.model.dao.LottoResultDao;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class LottoResultDaoTest {
    private static final LottoResultDao LOTTO_RESULT_DAO = new LottoResultDao();

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

        LOTTO_RESULT_DAO.saveResult(WebUILottoApplication.makeReadableLottoResult(lottoResult), money, lottoResult);

        int latestRound = LOTTO_RESULT_DAO.getLatestRound();
        LOTTO_RESULT_DAO.deleteLottoResult(latestRound);
    }

    @Test
    public void fetchRequestMoneyAndProfitTest() throws SQLException {
        Money money = new Money("4000");
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        lottoList.add(new Lotto(Arrays.asList(6,7,8,9,10,11)));
        lottoList.add(new Lotto(Arrays.asList(12,13,14,15,16,17)));
        lottoList.add(new Lotto(Arrays.asList(18,19,20,21,22,23)));
        Lottos lottos = new Lottos(lottoList);

        Lotto winningLottoTicket = new Lotto(Arrays.asList(1,2,3,41,42,43));
        LottoNumber bonusNumber = new LottoNumber(44);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

        LottoResult lottoResult = new LottoResult(money, lottos.getPrizes(winningLotto));

        LOTTO_RESULT_DAO.saveResult(WebUILottoApplication.makeReadableLottoResult(lottoResult), money, lottoResult);
        List<String> testSet = new ArrayList<>();
        testSet.add("당첨금액: \n");
        testSet.add("5000.0");
        testSet.add("수익률: \n");
        testSet.add("1.25%");

        int round = LOTTO_RESULT_DAO.getLatestRound();
        assertThat(LOTTO_RESULT_DAO.fetchRequestMoneyAndProfit(round -1)).isEqualTo(testSet);
        LOTTO_RESULT_DAO.deleteLottoResult(round);
    }

    @Test
    public void fetchRequestResultTest() throws SQLException {
        Money money = new Money("4000");
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        lottoList.add(new Lotto(Arrays.asList(6,7,8,9,10,11)));
        lottoList.add(new Lotto(Arrays.asList(12,13,14,15,16,17)));
        lottoList.add(new Lotto(Arrays.asList(18,19,20,21,22,23)));
        Lottos lottos = new Lottos(lottoList);

        Lotto winningLottoTicket = new Lotto(Arrays.asList(1,2,3,41,42,43));
        LottoNumber bonusNumber = new LottoNumber(44);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

        LottoResult lottoResult = new LottoResult(money, lottos.getPrizes(winningLotto));

        LOTTO_RESULT_DAO.saveResult(WebUILottoApplication.makeReadableLottoResult(lottoResult), money, lottoResult);
        List<String> testList = new ArrayList<>();
        testList.add("6개 일치 2000000000...0");
        testList.add("5개 일치 30000000...0");
        testList.add("5개 일치 1500000...0");
        testList.add("4개 일치 50000...0");
        testList.add("3개 일치 5000...1");
        testList.add("0개 일치 0...3\n");


        int round = LOTTO_RESULT_DAO.getLatestRound();
        assertThat(LOTTO_RESULT_DAO.fetchRequestResult(round -1)).isEqualTo(testList);
        LOTTO_RESULT_DAO.deleteLottoResult(round);
    }
}
