package lotto.dao;

import lotto.creator.ManualLottoCreator;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottosDaoTest {
    private LottosDao lottosDao;

    @BeforeEach
    void setUp() {
        lottosDao = new LottosDao();
    }

    @Test
    void add_lottos() throws Exception{
        ManualLottoCreator lottoCreator = new ManualLottoCreator(Arrays.asList(
                "1, 2, 3, 4, 5, 6",
                "1, 2, 3, 4, 5, 7",
                "7, 8, 9, 10, 11, 12"
        ));
        Lottos lottos = new Lottos(lottoCreator.create());
        int[] result = {1, 1, 1};
        assertThat(lottosDao.addLottos(lottos)).isEqualTo(result);
    }

    @Test
    public void connection() {
        Connection con = lottosDao.getConnection();
        assertNotNull(con);
    }
}