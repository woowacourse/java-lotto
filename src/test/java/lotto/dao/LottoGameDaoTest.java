package lotto.dao;

import lotto.dto.LottoGameDto;
import lotto.dto.ResultDto;
import lotto.dto.WinningNumberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameDaoTest {
    private static final int TEST_ROUND = 1;
    LottoGameDao lottoGameDao;
    WinningNumberDto initWinningNumberDto;
    ResultDto initResultDto;

    @BeforeEach
    void setUp() {
        lottoGameDao = new LottoGameDao();
        initWinningNumberDto = new WinningNumberDto();
        initWinningNumberDto.setNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        initWinningNumberDto.setBonusBall(7);

        initResultDto = new ResultDto();
        Map<String, Integer> initPrize = new HashMap<>();
        initPrize.put("first", 1);
        initPrize.put("second", 0);
        initPrize.put("third", 1);
        initPrize.put("fourth", 0);
        initPrize.put("fifth", 0);
        initPrize.put("none", 0);
        initResultDto.setPrize(initPrize);
        initResultDto.setWinningMoney(2_003_000_000);
    }

    @Test
    void 현재_라운드가_1인지_확인_테스트() throws SQLException {
        assertThat(lottoGameDao.currentRound()).isEqualTo(1);
    }

    @Test
    @Order(1)
    void 라운드_추가_조회_테스트() throws SQLException {
        LottoDao lottoDao = new LottoDao();
        lottoDao.removeAllLotto(TEST_ROUND);
        lottoGameDao.removeWinningNumber(TEST_ROUND);
        lottoGameDao.removeResult(TEST_ROUND);
        lottoGameDao.removeRound(TEST_ROUND);

        LottoGameDto lottoGameDto = new LottoGameDto();
        lottoGameDto.setRound(TEST_ROUND);
        lottoGameDto.setMoney(2000);
        lottoGameDto.setCountOfManual(2);

        lottoGameDao.addRound(lottoGameDto);
        assertThat(lottoGameDao.findRoundById(TEST_ROUND).getRound()).isEqualTo(1);
    }

    @Test
    @Order(2)
    void 해당_라운드의_당첨번호_조회() throws SQLException {
        lottoGameDao.addWinningNumber(TEST_ROUND, initWinningNumberDto);
        WinningNumberDto winningNumberDto = lottoGameDao.findWinningLottoByRound(TEST_ROUND);
        assertThat(winningNumberDto.getNumbers()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(winningNumberDto.getBonusBall()).isEqualTo(7);
    }

    @Test
    @Order(3)
    void 해당_라운드의_로또_당첨_결과_조회() throws SQLException {
        lottoGameDao.addResult(TEST_ROUND, initResultDto);
        ResultDto resultDto = lottoGameDao.findResultByRound(TEST_ROUND);
        Map<String, Integer> prize = resultDto.getPrize();
        assertThat(prize.get("first")).isEqualTo(1);
        assertThat(prize.get("third")).isEqualTo(1);
        assertThat(resultDto.getWinningMoney()).isEqualTo(2_003_000_000);
    }
}
