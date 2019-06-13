package lotto.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameDaoTest {
    private static final int TEST_ROUND = 1;
    LottoGameDao lottoGameDao;

    @BeforeEach
    void setUp() {
        lottoGameDao = new LottoGameDao();
    }

    @Test
    void 라운드_추가_조회_테스트() throws SQLException {
        lottoGameDao.removeRound(TEST_ROUND);
        int money = 2000;
        int countOfManual = 2;
        lottoGameDao.addRound(TEST_ROUND, money, countOfManual);

        assertThat(lottoGameDao.findRoundById(TEST_ROUND).getRound()).isEqualTo(1);
    }
}
