package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.persistence.dto.LottoDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDaoTest {
    private static final LottoDto TEMP_LOTTO;

    private static LottoDao lottoDao;
    private static Queue<Long> lottoIdsToClean = new LinkedList<>();

    static {
        TEMP_LOTTO = new LottoDto();
        TEMP_LOTTO.setNumber0(22);
        TEMP_LOTTO.setNumber1(33);
        TEMP_LOTTO.setNumber2(44);
        TEMP_LOTTO.setNumber3(31);
        TEMP_LOTTO.setNumber4(29);
        TEMP_LOTTO.setNumber5(17);
        TEMP_LOTTO.setPrice(1000);
    }

    @BeforeAll
    static void init() {
        lottoDao = new LottoDao(ConnectionFactory.getConnection());
    }

    @AfterAll
    static void cleanup() throws SQLException {
        for (long id : lottoIdsToClean) {
            lottoDao.deleteById(id);
        }
    }

    @Test
    void insertAndFind() throws SQLException {
        long insertedLottoId = lottoDao.addLotto(TEMP_LOTTO);
        Optional<LottoDto> found = lottoDao.findById(insertedLottoId);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getNumber0()).isEqualTo(TEMP_LOTTO.getNumber0());
        assertThat(found.get().getNumber1()).isEqualTo(TEMP_LOTTO.getNumber1());
        assertThat(found.get().getNumber2()).isEqualTo(TEMP_LOTTO.getNumber2());
        assertThat(found.get().getNumber3()).isEqualTo(TEMP_LOTTO.getNumber3());
        assertThat(found.get().getNumber4()).isEqualTo(TEMP_LOTTO.getNumber4());
        assertThat(found.get().getNumber5()).isEqualTo(TEMP_LOTTO.getNumber5());
        lottoIdsToClean.add(insertedLottoId);
    }

    @Test
    void delete() throws SQLException {
        long insertedLottoId = lottoDao.addLotto(TEMP_LOTTO);
        assertThat(lottoDao.deleteById(insertedLottoId)).isEqualTo(1);
        assertThat(lottoDao.findById(insertedLottoId).isPresent()).isFalse();
    }
}
