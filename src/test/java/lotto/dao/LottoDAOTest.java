package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDAOTest {
    static final Lotto LOTTO_TEST = new Lotto(new HashSet<>(
            Arrays.asList(
                    LottoNumber.get(1),
                    LottoNumber.get(2),
                    LottoNumber.get(3),
                    LottoNumber.get(43),
                    LottoNumber.get(44),
                    LottoNumber.get(45)
            )));

    private LottoDAO lottoDAO;
    private RoundDAO roundDAO;

    @BeforeEach
    void setUp() throws SQLException {
        lottoDAO = LottoDAO.getInstance();
        roundDAO = RoundDAO.getInstance();
        roundDAO.addRound(RoundDAOTest.AMOUNT_TEST);
    }

    @Test
    public void findLottosByRound() throws SQLException {
        lottoDAO.addLottos(Arrays.asList(LOTTO_TEST), roundDAO.findMaxRound());
        List<Lotto> lottos = lottoDAO.findLottosByRound(roundDAO.findMaxRound());
        assertThat(lottos.get(0)).isEqualTo(LOTTO_TEST);
        removeLotto();
    }

    @Test
    public void addLottos() throws SQLException {
        lottoDAO.addLottos(Arrays.asList(LOTTO_TEST), roundDAO.findMaxRound());
        removeLotto();
    }

    @Test
    public void removeLotto() throws SQLException {
        lottoDAO.removeLotto(roundDAO.findMaxRound());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        roundDAO.removeRound(roundDAO.findMaxRound());
        roundDAO = null;
        lottoDAO = null;
    }
}
