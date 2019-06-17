package lotto.dao;

import lotto.dto.StatDto;
import lotto.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatDaoTest {
    private static final WinningLotto WINNING_LOTTO;
    private static final List<Lotto> LOTTOS;
    private static final GameResultMatcher GAME_STAT;
    private static final StatDto GAME_STAT_DTO;

    private static final int TEST_ROUND = 2;
    private static final double DEFAULT_PROFIT_RATE = 0.0;
    private static final Map<Rank, Integer> DEFAULT_MAP = new HashMap<>();

    private StatDao statDao;

    static {
        final Lotto sampleLotto = new LottoFactory().create(Arrays.asList(1, 2, 3, 4, 5, 6));
        final LottoNumber sampleBonus = LottoNumber.of(7);
        WINNING_LOTTO = WinningLotto.of(sampleLotto, sampleBonus);

        LOTTOS = new ArrayList<>();
        LOTTOS.add(new LottoFactory().create(Arrays.asList(1, 2, 3, 4, 5, 6)));
        LOTTOS.add(new LottoFactory().create(Arrays.asList(1, 2, 3, 4, 5, 7)));
        LOTTOS.add(new LottoFactory().create(Arrays.asList(1, 2, 3, 4, 8, 9)));
        LOTTOS.add(new LottoFactory().create(Arrays.asList(1, 2, 3, 8, 9, 10)));
        LOTTOS.add(new LottoFactory().create(Arrays.asList(1, 2, 8, 9, 10, 11)));

        GAME_STAT = GameResultMatcher.of(LOTTOS);
        GAME_STAT.match(WINNING_LOTTO);

        GAME_STAT_DTO = StatDto.of(GAME_STAT);
    }

    @BeforeEach
    public void setUp() {
        statDao = StatDao.getInstance();
        statDao.deleteAll();
        statDao.add(GAME_STAT_DTO, TEST_ROUND);
    }

    @Test
    public void add() {
        statDao.add(GAME_STAT_DTO, 1);
        StatDto actual = statDao.findByRound(1);
        assertEquals(GAME_STAT_DTO, actual);
    }

    @Test
    public void findByRound() {
        StatDto actual = statDao.findByRound(TEST_ROUND);
        assertEquals(GAME_STAT_DTO, actual);
    }

    @Test
    public void deleteAll() {
        statDao.deleteAll();
        StatDto expected = StatDto.of(DEFAULT_MAP, DEFAULT_PROFIT_RATE);
        assertEquals(expected, statDao.findByRound(TEST_ROUND));
    }

    @AfterEach
    public void tearDown() {
        statDao.deleteAll();
    }

}