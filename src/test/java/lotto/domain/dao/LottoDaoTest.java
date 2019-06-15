package lotto.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class LottoDaoTest {
    private LottoDao lottoDao;

    @BeforeEach
    void setUp() {
        lottoDao = LottoDao.getInstance();
    }

    @Test
    void insertUser() throws Exception {
        String name = "tester";
        assertThat(lottoDao.insertUser(name)).isEqualTo(1);
    }
}
