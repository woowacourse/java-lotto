package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.LottosResult;
import lotto.domain.creator.CreatorFactory;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LottoResultDAOTest {
    private LottoResultDAO lottoResultDAO;

    @BeforeEach
    void setUp() {
        this.lottoResultDAO = new LottoResultDAO(new DBConnector());
    }

    @Test
    void crdLottoResult() throws Exception {
        LottoGameDAO lottoGameDAO = new LottoGameDAO(new DBConnector());
        lottoGameDAO.addLottoGame(0);

        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "12");

        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 12",
                                            "2, 3, 4, 5, 6, 7", "3, 4, 5, 6, 7, 8",
                                            "4, 5, 6, 7, 8, 9", "5, 6, 7, 8, 9, 10",
                                            "6, 7, 8, 9, 10, 11");

        CreatorFactory factory = new CreatorFactory(inputs);
        Lottos myLottos = LottoFactory.createLottos(factory.createCreators(7, 0));
        LottosResult result = new LottosResult(winningLotto, myLottos);

        assertEquals(lottoResultDAO.addLottoResult(result, 0), 1);
        assertEquals(lottoResultDAO.findByRound(0), 2031555000);
        assertEquals(lottoResultDAO.deleteLottoResult(0), 1);

        lottoGameDAO.deleteLottoGame(0);
    }
}