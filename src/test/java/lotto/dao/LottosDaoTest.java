package lotto.dao;

import lotto.DataBase;
import lotto.creator.ManualLottoCreator;
import lotto.domain.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottosDaoTest {
    private LottosDao lottosDao;

    @BeforeEach
    void setUp() {
        lottosDao = new LottosDao(new DataBase());
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
        assertThat(lottosDao.addLottos(lottos, 0)).isEqualTo(result);
    }

    @Test
    void findByTimes() throws Exception{
        ManualLottoCreator lottoCreator = new ManualLottoCreator(Arrays.asList(
                "1, 2, 3, 4, 5, 6",
                "1, 2, 3, 4, 5, 7",
                "7, 8, 9, 10, 11, 12"
        ));
        Lottos lottos = new Lottos(lottoCreator.create());
        assertThat(lottosDao.findByTimes(1)).isEqualTo(lottos);
    }

}