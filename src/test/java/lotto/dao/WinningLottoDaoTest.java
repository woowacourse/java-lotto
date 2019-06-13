package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WinningLottoDaoTest {
    private static final List<Integer> NUMBERS;
    private static final Lotto SAMPLE_LOTTO;
    private static final LottoNumber SAMPLE_BONUS;
    private static final WinningLotto WINNING_LOTTO;

    private WinningLottoDao winningLottoDao;
    private static final int TEST_ROUND = 2;

    static {
        NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6);
        SAMPLE_LOTTO = new LottoFactory().create(NUMBERS);
        SAMPLE_BONUS = LottoNumber.of(8);
        WINNING_LOTTO = WinningLotto.of(SAMPLE_LOTTO, SAMPLE_BONUS);
    }

    @BeforeEach
    public void setUp() {
        winningLottoDao = WinningLottoDao.getInstance();
        winningLottoDao.add(WINNING_LOTTO, TEST_ROUND);
    }

    @Test
    public void add() {
        winningLottoDao.add(WINNING_LOTTO, 1);
        WinningLotto actual = winningLottoDao.findByRound(1);
        assertEquals(WINNING_LOTTO, actual);
    }

    @Test
    public void findByRound() {
        WinningLotto actual = winningLottoDao.findByRound(TEST_ROUND);
        assertEquals(WINNING_LOTTO, actual);
    }

    @Test
    public void delete() {
        winningLottoDao.deleteAll();
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Optional.ofNullable(winningLottoDao.findByRound(1)).orElseThrow(IllegalArgumentException::new);
        });
    }

    @AfterEach
    public void tearDown() {
        winningLottoDao.deleteAll();
    }
}