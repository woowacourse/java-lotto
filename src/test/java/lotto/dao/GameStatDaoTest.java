package lotto.dao;

import lotto.dto.GameStatDto;
import lotto.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStatDaoTest {
    private static final WinningLotto WINNING_LOTTO;
    private static final List<Lotto> LOTTOS;
    private static final GameResultMatcher GAME_STAT;
    private static final GameStatDto GAME_STAT_DTO;

    private static final int TEST_TURN = 2;
    private static final double DEFAULT_PROFIT_RATE = 0.0;
    private static final Map<Rank, Integer> DEFAULT_MAP = new HashMap<>();

    private GameStatDao statDao;

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

        GAME_STAT_DTO = GameStatDto.of(GAME_STAT);
    }

    @BeforeEach
    public void setUp() {
        statDao = GameStatDao.getInstance();
        statDao.deleteAll();
        statDao.add(GAME_STAT_DTO, TEST_TURN);
    }

    @Test
    public void add() {
        statDao.add(GAME_STAT_DTO, 1);
        GameStatDto actual = statDao.findByTurn(1);
        assertEquals(GAME_STAT_DTO, actual);
    }

    @Test
    public void findByTurn() {
        GameStatDto actual = statDao.findByTurn(TEST_TURN);
        assertEquals(GAME_STAT_DTO, actual);
    }

    @Test
    public void deleteAll() {
        statDao.deleteAll();
        GameStatDto expected = GameStatDto.of(DEFAULT_MAP, DEFAULT_PROFIT_RATE);
        assertEquals(expected, statDao.findByTurn(TEST_TURN));
    }

    @AfterEach
    public void tearDown() {
        statDao.deleteAll();
    }

}