package lotto.domain.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LottoDaoTest {
    private LottoDao lottoDAO;
    @BeforeEach
    void setUp() {
        lottoDAO = new LottoDao();
    }

    @Test
    void connection() {
        Connection connection = lottoDAO.getConnection();
        assertNotNull(connection);
    }

    @Test
    void addLottos() throws SQLException {
        List<String> manualLottos = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7");
        Lottos lottos = new Lottos(manualLottos, 2);
        lottoDAO.addTotalLottos(2, lottos);
    }

    @Test
    void findLottoByRound() throws SQLException {
        Lotto lotto = new Lotto(Arrays.asList(LottoNumber.from(1),LottoNumber.from(2),LottoNumber.from(3),LottoNumber.from(4),LottoNumber.from(5),LottoNumber.from(6)));
        List<Lotto> expected = new ArrayList<>();
        expected.add(lotto);
        lotto = new Lotto(Arrays.asList(LottoNumber.from(2),LottoNumber.from(3),LottoNumber.from(4),LottoNumber.from(5),LottoNumber.from(6),LottoNumber.from(7)));
        expected.add(lotto);
        assertEquals(expected, lottoDAO.findLottoByRound(2));
    }

    @Test
    void deleteLottos() throws SQLException {
        lottoDAO.deleteLottos(2);
    }
}
