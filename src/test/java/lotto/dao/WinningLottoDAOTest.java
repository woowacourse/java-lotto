package lotto.dao;

import lotto.domain.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDAO;
    private WinningLotto winningLotto;
    private RoundDAO roundDAO;
    private String lottoRound;

    @BeforeEach
    void setup_db() throws SQLException {
        Connection connection = DBManager.getConnection();
        winningLottoDAO = new WinningLottoDAO(connection);
        roundDAO = new RoundDAO(connection);
        lottoRound = roundDAO.getCurrentRound().toString();
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

    @Test
    void test0_프라이머리키_튜플_생성() throws SQLException {
        roundDAO.addRound(roundDAO.getNextRound().toString());
    }

    @Test
    void test1_당첨_로또_추가() throws SQLException {
        winningLottoDAO.addWinningLotto(lottoRound, winningLotto);
    }

    @Test
    void test2_당첨_로또_검색() throws SQLException {
        assertThat(winningLotto).isEqualTo(winningLottoDAO.findByLottoRound(lottoRound));
    }

    @Test
    void test3_프라이머리키_튜플_삭제() throws SQLException {
        roundDAO.deleteRound(lottoRound);
    }
}
