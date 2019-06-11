package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.ManualLottoMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class LottoDAOTest {
    private LottoDAO lottoDAO;
    private Lottos lottos;
    private RoundDAO roundDAO;
    private String lottoRound;

    @BeforeEach
    public void setup_db() throws SQLException {
        Connection connection = DBManager.getConnection();
        roundDAO = new RoundDAO(connection);
        lottoDAO = new LottoDAO(connection);
        lottoRound = roundDAO.getCurrentRound().toString();
    }

    @BeforeEach
    public void setup_lottos() {
        ManualLottoMachine manualLottoMachine = new ManualLottoMachine(
                Arrays.asList(
                        "1,2,3,4,5,6",
                        "11,12,13,14,15,16",
                        "21,22,23,24,25,26"
                ));
        lottos = new Lottos(manualLottoMachine.generateLottos());
    }

    @Test
    void test0_프라이머리키_튜플_생성() throws SQLException {
        roundDAO.addRound(roundDAO.getNextRound().toString());
    }

    @Test
    void test1_로또_추가() throws SQLException {
        lottoDAO.addLottos(lottoRound, lottos);
    }

    @Test
    void test2_로또_검색() throws SQLException {
        assertThat(lottos).isEqualTo(lottoDAO.findByLottoRound(lottoRound));
    }

    @Test
    void test3_프라이머리키_튜플_삭제() throws SQLException {
        roundDAO.deleteRound(lottoRound);
    }
}
