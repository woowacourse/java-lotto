package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LottoDAOTest {
    private static final Lotto LOTTO_TEST = new Lotto(new HashSet<>(
            Arrays.asList(
                    LottoNumber.get(1),
                    LottoNumber.get(2),
                    LottoNumber.get(3),
                    LottoNumber.get(43),
                    LottoNumber.get(44),
                    LottoNumber.get(45)
            )));

    private LottoDAO lottoDAO;

    @BeforeEach
    void setUp() {
        lottoDAO = new LottoDAO();
    }

    @Test
    public void connection() {
        Connection con = lottoDAO.getConnection();
        assertNotNull(con);
        lottoDAO.closeConnection(con);
    }

    @Test
    public void findMaxRound() throws SQLException {
        int maxRound = lottoDAO.findMaxRound();
        assertThat(maxRound).isEqualTo(0);
    }

    @Test
    public void findLottosByRound() throws SQLException {
        lottoDAO.addLottos(Arrays.asList(LOTTO_TEST));
        List<Lotto> lottos = lottoDAO.findLottosByRound(lottoDAO.findMaxRound());
        assertThat(lottos.get(0)).isEqualTo(LOTTO_TEST);
        removeLotto();
    }

    @Test
    public void addLottos() throws SQLException {
        lottoDAO.addLottos(Arrays.asList(LOTTO_TEST));
        removeLotto();
    }

    @Test
    public void removeLotto() throws SQLException {
        lottoDAO.removeLotto(lottoDAO.findMaxRound());
    }
}
