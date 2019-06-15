package lotto.dao;

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
    ResultDto initResultDto;

    @BeforeEach
    void setUp() {
        resultDao = ResultDao.getInstance();

        initResultDto = new ResultDto();
        Map<String, Integer> initPrize = new HashMap<>();
        initPrize.put("first", 1);
        initPrize.put("second", 0);
        initPrize.put("third", 1);
        initPrize.put("fourth", 0);
        initPrize.put("fifth", 0);
        initPrize.put("none", 0);
        initResultDto.setPrizeResult(initPrize);
        initResultDto.setWinningMoney(2_003_000_000);
    }

    @Test
    void 첫번째_라운드의_결과_일치_여부_테스트() throws SQLException {
        ResultDto resultDto = resultDao.findResultByRound(TEST_ROUND);
        Map<String, Integer> prize = resultDto.getPrizeResult();
        assertThat(prize.get("first")).isEqualTo(1);
        assertThat(prize.get("second")).isEqualTo(0);
        assertThat(prize.get("third")).isEqualTo(1);
        assertThat(prize.get("fourth")).isEqualTo(0);
        assertThat(prize.get("fifth")).isEqualTo(0);
        assertThat(prize.get("none")).isEqualTo(0);
        assertThat(resultDto.getWinningMoney()).isEqualTo(2_003_000_000);
    }
}
