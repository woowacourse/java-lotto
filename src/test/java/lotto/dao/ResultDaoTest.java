package lotto.dao;

import lotto.domain.Prize;
import lotto.domain.Result;
import lotto.dto.ResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static lotto.dao.LottoGameDaoTest.TEST_ROUND;
import static org.assertj.core.api.Assertions.assertThat;

public class ResultDaoTest {

    ResultDao resultDao;
    Result initResult;

    @BeforeEach
    void setUp() {
        resultDao = ResultDao.getInstance();

        Map<Prize, Integer> initPrize = new HashMap<>();
        initPrize.put(Prize.FIRST, 1);
        initPrize.put(Prize.SECOND, 0);
        initPrize.put(Prize.THIRD, 1);
        initPrize.put(Prize.FOURTH, 0);
        initPrize.put(Prize.FIFTH, 0);
        initPrize.put(Prize.NONE, 0);
        initResult = new Result(initPrize);
    }

    @Test
    void 첫번째_라운드의_결과_일치_여부_테스트() throws SQLException {
        ResultDto resultDto = resultDao.findResultByRound(TEST_ROUND);
        assertThat(resultDto.toEntity()).isEqualTo(initResult);
    }
}
