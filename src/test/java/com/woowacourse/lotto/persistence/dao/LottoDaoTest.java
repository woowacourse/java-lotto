package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.persistence.dto.LottoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDaoTest {
    private static final LottoDto TEMP_LOTTO;
    private LottoDao dao;
    private long lastGeneratedId;

    static {
        TEMP_LOTTO = new LottoDto();
        TEMP_LOTTO.setNumber0(2);
        TEMP_LOTTO.setNumber1(7);
        TEMP_LOTTO.setNumber2(11);
        TEMP_LOTTO.setNumber3(17);
        TEMP_LOTTO.setNumber4(28);
        TEMP_LOTTO.setNumber5(30);
        TEMP_LOTTO.setPrice(Lotto.UNIT_PRICE);
    }

    @BeforeEach
    void setup() {
        dao = new LottoDao(ConnectionFactory.getConnection());
    }

    @AfterEach
    void cleanup() throws Exception {
        dao.deleteById(lastGeneratedId);
    }

    @Test
    void insert() throws Exception {
        long insertedId = dao.addLotto(TEMP_LOTTO);
        assertThat(insertedId).isGreaterThanOrEqualTo(1);
        lastGeneratedId = insertedId;
    }

    @Test
    void findById() throws SQLException {
        long insertedId = dao.addLotto(TEMP_LOTTO);
        lastGeneratedId = insertedId;
        Optional<LottoDto> maybeFound = dao.findById(insertedId);
        assertThat(maybeFound.isPresent()).isTrue();
        LottoDto found = maybeFound.get();
        assertThat(found.getNumber0()).isEqualTo(TEMP_LOTTO.getNumber0());
        assertThat(found.getNumber1()).isEqualTo(TEMP_LOTTO.getNumber1());
        assertThat(found.getNumber2()).isEqualTo(TEMP_LOTTO.getNumber2());
        assertThat(found.getNumber3()).isEqualTo(TEMP_LOTTO.getNumber3());
        assertThat(found.getNumber4()).isEqualTo(TEMP_LOTTO.getNumber4());
        assertThat(found.getNumber5()).isEqualTo(TEMP_LOTTO.getNumber5());
    }

    @Test
    void delete() throws Exception {
        long insertedId = dao.addLotto(TEMP_LOTTO);
        lastGeneratedId = insertedId;
        assertThat(dao.deleteById(insertedId)).isEqualTo(1);
        assertThat(dao.findById(insertedId).isPresent()).isFalse();
    }
}
