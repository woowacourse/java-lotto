package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.persistence.dto.WinningLottoDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDaoTest {
    private static final WinningLottoDto TEMP_WINNING_LOTTO;

    private static WinningLottoDao winningLottoDao;
    private static Queue<Long> winningLottoIdsToClean = new LinkedList<>();

    static {
        TEMP_WINNING_LOTTO = new WinningLottoDto();
        TEMP_WINNING_LOTTO.setWinningNumber0(31);
        TEMP_WINNING_LOTTO.setWinningNumber1(13);
        TEMP_WINNING_LOTTO.setWinningNumber2(14);
        TEMP_WINNING_LOTTO.setWinningNumber3(19);
        TEMP_WINNING_LOTTO.setWinningNumber4(26);
        TEMP_WINNING_LOTTO.setWinningNumber5(28);
        TEMP_WINNING_LOTTO.setWinningBonusNumber(30);
    }

    @BeforeAll
    static void init() {
        winningLottoDao = new WinningLottoDao(ConnectionFactory.getConnection());
    }

    @BeforeAll
    static void cleanup() throws SQLException {
        for (long id : winningLottoIdsToClean) {
            winningLottoDao.deleteById(id);
        }
    }

    @Test
    void insertAndFind() throws SQLException {
        long insertedWinningLottoId = winningLottoDao.addWinningLotto(TEMP_WINNING_LOTTO);
        Optional<WinningLottoDto> found = winningLottoDao.findById(insertedWinningLottoId);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getWinningNumber0()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber0());
        assertThat(found.get().getWinningNumber1()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber1());
        assertThat(found.get().getWinningNumber2()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber2());
        assertThat(found.get().getWinningNumber3()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber3());
        assertThat(found.get().getWinningNumber4()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber4());
        assertThat(found.get().getWinningNumber5()).isEqualTo(TEMP_WINNING_LOTTO.getWinningNumber5());
        assertThat(found.get().getWinningBonusNumber()).isEqualTo(TEMP_WINNING_LOTTO.getWinningBonusNumber());
        winningLottoIdsToClean.add(insertedWinningLottoId);
    }

    @Test
    void delete() throws SQLException {
        long insertedWinningLottoId = winningLottoDao.addWinningLotto(TEMP_WINNING_LOTTO);
        winningLottoDao.deleteById(insertedWinningLottoId);
        Optional<WinningLottoDto> found = winningLottoDao.findById(insertedWinningLottoId);
        assertThat(found.isPresent()).isFalse();
    }
}
