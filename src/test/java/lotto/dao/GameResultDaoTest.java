package lotto.dao;

import lotto.dto.GameResultDto;
import lotto.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameResultDaoTest {
    private static final WinningLotto WINNING_LOTTO;
    private static final List<Lotto> LOTTOS;
    private static final GameResultMatcher GAME_RESULT;
    private static final GameResultDto GAME_RESULT_DTO;

    private static final int TEST_TURN = 2;
    private static final double DEFAULT_PROFIT_RATE = 0.0;
    private static final Map<Rank, Integer> DEFAULT_MAP = new HashMap<>();

    private GameResultDao resultDao;

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

        GAME_RESULT = GameResultMatcher.of(LOTTOS);
        GAME_RESULT.match(WINNING_LOTTO);

        GAME_RESULT_DTO = GameResultDto.of(GAME_RESULT);
    }

    @BeforeEach
    public void setUp() {
        resultDao = GameResultDao.getInstance();
        resultDao.deleteAll();
        resultDao.add(GAME_RESULT_DTO, TEST_TURN);
    }

    @Test
    public void add() {
        resultDao.add(GAME_RESULT_DTO, 1);
        GameResultDto actual = resultDao.findByTurn(1);
        assertEquals(GAME_RESULT_DTO, actual);
    }

    @Test
    public void findByTurn() {
        GameResultDto actual = resultDao.findByTurn(TEST_TURN);
        assertEquals(GAME_RESULT_DTO, actual);
    }

    @Test
    public void deleteAll() {
        resultDao.deleteAll();
        GameResultDto expected = GameResultDto.of(DEFAULT_MAP, DEFAULT_PROFIT_RATE);
        assertEquals(expected, resultDao.findByTurn(TEST_TURN));
    }

    @AfterEach
    public void tearDown() {
        resultDao.deleteAll();
    }

}