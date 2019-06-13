package lotto.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class LottoDaoTest {
    private LottoDao lottoDao;

    @BeforeEach
    void setUp() {
        lottoDao = LottoDao.getInstance();
    }

    @Test
    void selectAllLottoGame() throws SQLException {
        // TODO: 2019-06-12 HOW TO TEST????
        assertThat(lottoDao.selectAllLottoGame()).isNotNull();
    }

    @Test
    void insertUser() {
        String name = "tester";
        assertThat(lottoDao.insertUser(name)).isEqualTo(1);
    }
}
