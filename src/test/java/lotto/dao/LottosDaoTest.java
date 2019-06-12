package lotto.dao;

import lotto.db.DatabaseConnection;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class LottosDaoTest {

    private Connection conn;
    private LottosDao lottosDAO;
    private List<Lotto> lottos;

    @BeforeEach
    public void setUp() {
        conn = new DatabaseConnection().getConnection();
        lottosDAO = new LottosDao(conn);
        lottos = Arrays.asList(new Lotto(Arrays.asList(1,2,3,4,5,6)));
    }

    @Test
    public void Lottos_추가() throws SQLException {
        lottosDAO.addLottos(1,new Lottos(lottos));
    }

    @Test
    public void Lotto_조회() throws SQLException {
        lottosDAO.addLottos(1,new Lottos(lottos));
        assertThat(lottosDAO.findLottoByRound(1)).isEqualTo(new Lottos(lottos));
    }

}