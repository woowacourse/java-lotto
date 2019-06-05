package com.woowacourse.lotto.domain.dao;

import com.woowacourse.lotto.domain.dto.WinningLottoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDaoTest {
    private static final WinningLottoDto TEMP_WINNING_LOTTO;

    private WinningLottoDao dao;
    private long lastInsertedId;

    static {
        TEMP_WINNING_LOTTO = new WinningLottoDto();
        TEMP_WINNING_LOTTO.setWinningNumber0(1);
        TEMP_WINNING_LOTTO.setWinningNumber1(2);
        TEMP_WINNING_LOTTO.setWinningNumber2(3);
        TEMP_WINNING_LOTTO.setWinningNumber3(4);
        TEMP_WINNING_LOTTO.setWinningNumber4(5);
        TEMP_WINNING_LOTTO.setWinningNumber5(6);
        TEMP_WINNING_LOTTO.setWinningBonusNumber(7);
    }

    @BeforeEach
    void setup() {
        dao = new WinningLottoDao(ConnectionFactory.getConnection());
    }

    @AfterEach
    void cleanup() throws Exception {
        dao.deleteById(lastInsertedId);
    }

    @Test
    void insert() throws Exception {
        long insertedId = dao.addWinningLotto(TEMP_WINNING_LOTTO);
        lastInsertedId = insertedId;
        assertThat(insertedId).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findById() throws Exception {
        long insertedId = dao.addWinningLotto(TEMP_WINNING_LOTTO);
        lastInsertedId = insertedId;
        Optional<WinningLottoDto> maybeFound = dao.findById(insertedId);
        assertThat(maybeFound.isPresent()).isTrue();
        WinningLottoDto found = maybeFound.get();
        assertThat(found.getWinningNumber0()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber0());
        assertThat(found.getWinningNumber1()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber1());
        assertThat(found.getWinningNumber2()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber2());
        assertThat(found.getWinningNumber3()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber3());
        assertThat(found.getWinningNumber4()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber4());
        assertThat(found.getWinningNumber5()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber5());
        assertThat(found.getWinningBonusNumber()).isEqualTo(TEMP_WINNING_LOTTO.getWinningBonusNumber());
    }

    @Test
    void deleteById() throws Exception{
        long insertedId = dao.addWinningLotto(TEMP_WINNING_LOTTO);
        lastInsertedId = insertedId;
        assertThat(dao.deleteById(insertedId)).isEqualTo(1);
        assertThat(dao.findById(insertedId).isPresent()).isFalse();
    }
}
