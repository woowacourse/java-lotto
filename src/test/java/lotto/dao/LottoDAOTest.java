package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LottoDAOTest {
    private LottoDAO lottoDAO;
    private Lottos lottos;

    @BeforeEach
    public void setup_db() {
        lottoDAO = new LottoDAO();
    }

    @BeforeEach
    public void setup_lottos() {
        Lotto lotto1 = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(6)
        ));
        Lotto lotto2 = new Lotto(Arrays.asList(
                LottoNumber.getInstance(11),
                LottoNumber.getInstance(12),
                LottoNumber.getInstance(13),
                LottoNumber.getInstance(14),
                LottoNumber.getInstance(15),
                LottoNumber.getInstance(16)
        ));
        Lotto lotto3 = new Lotto(Arrays.asList(
                LottoNumber.getInstance(21),
                LottoNumber.getInstance(22),
                LottoNumber.getInstance(23),
                LottoNumber.getInstance(24),
                LottoNumber.getInstance(25),
                LottoNumber.getInstance(26)
        ));
        lottos = new Lottos(Arrays.asList(
                lotto1, lotto2, lotto3
        ));
    }

    @Test
    public void connection() {
        Connection con = lottoDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void test1_로또_추가() throws SQLException {
        lottoDAO.addLottos("1", lottos);
    }

    @Test
    void test2_로또_검색() throws SQLException {
        assertThat(lottos).isEqualTo(lottoDAO.findByLottoId("1"));
    }
}
