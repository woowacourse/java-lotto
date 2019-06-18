package lotto.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class LottoResultDaoTest {

    private LottoResultDao lottoResultDao;

    @BeforeEach
    void setUp() {
        lottoResultDao = LottoResultDao.getInstance();
    }

    @Test
    void selectAllLottoGame() throws Exception {
        assertThat(lottoResultDao.selectAllLottoResult()).isNotNull();
    }
}
