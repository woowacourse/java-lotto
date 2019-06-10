package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDAO;
    private WinningLotto winningLotto;
    private Lottos lottos;
    private LottoDAO lottoDAO;
    private String lottoId;

    @BeforeEach
    void setup_db() throws SQLException {
        Connection connection = DBManager.getConnection();
        winningLottoDAO = new WinningLottoDAO(connection);
        lottoDAO = new LottoDAO(connection);
        lottoId = lottoDAO.getRound().toString();
    }

    @BeforeEach
    void setup_winningLotto() {
        Lotto lastWinningLotto = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(6)
        ));
        LottoNumber bonusBall = LottoNumber.getInstance(7);
        winningLotto = new WinningLotto(lastWinningLotto, bonusBall);
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
    void test1_당첨_로또_추가() throws SQLException {
        lottoDAO.addLottos(lottoId, lottos);
        winningLottoDAO.addWinningLotto(lottoId, winningLotto);
    }

    @Test
    void test2_당첨_로또_검색() throws SQLException {
        assertThat(winningLotto).isEqualTo(winningLottoDAO.findByLottoId(lottoId).getWinningLotto());
    }

    @Test
    void test3_테이블_삭제() throws SQLException {
        lottoDAO.deleteLotto(lottoId);
    }
}
