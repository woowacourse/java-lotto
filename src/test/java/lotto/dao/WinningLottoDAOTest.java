package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

public class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDAO;
    private Lotto winningLotto;
    private LottoNumber bonusBall;
    private Lottos lottos;

    @BeforeEach
    void setup_db() {
        winningLottoDAO = new WinningLottoDAO(DBManager.getConnection());
    }

    @BeforeEach
    void setup_winningLotto() {
        winningLotto = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(6)
        ));
        bonusBall = LottoNumber.getInstance(7);
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
        LottoDAO lottoDAO = new LottoDAO(DBManager.getConnection());
        lottoDAO.addLottos("1", lottos);
        winningLottoDAO.addWinningLotto("1", winningLotto, bonusBall);
    }
}
