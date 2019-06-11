package lotto.dao;

import lotto.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class LottoResultDAOTest {
    private LottoResultDAO lottoResultDAO;
    private LottoResult lottoResult;
    private Lottos lottos;
    private RoundDAO roundDAO;
    private String lottoRound;

    @BeforeEach
    void setup_db() throws SQLException {
        Connection connection = DBManager.getConnection();
        lottoResultDAO = new LottoResultDAO(connection);
        roundDAO = new RoundDAO(connection);
        lottoRound = roundDAO.getCurrentRound().toString();
    }

    @BeforeEach
    void setup_lottoResult() {
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
        lottos = new Lottos(Arrays.asList(lotto1, lotto2, lotto3));

        Lotto lastWinningLotto = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(6)
        ));
        LottoNumber bonusBall = LottoNumber.getInstance(7);
        lottoResult = new LottoResult(new WinningLotto(lastWinningLotto, bonusBall), lottos);
    }

    @Test
    void test0_프라이머리키_튜플_생성() throws  SQLException {
        roundDAO.addRound(roundDAO.getNextRound().toString());
    }

    @Test
    void test1_로또_결과_추가() throws SQLException {
        lottoResultDAO.addLottoResult(lottoRound, lottoResult);
    }

    @Test
    void test2_프라이머리키_튜플_삭제() throws SQLException {
        roundDAO.deleteRound(lottoRound);
    }
}
