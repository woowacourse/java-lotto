package lotto.dao;

import lotto.dto.LottoGameDto;
import lotto.dto.WinningNumberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameDaoTest {
    static final int TEST_ROUND = 1;
    LottoGameDao lottoGameDao;
    WinningNumberDto initWinningNumberDto;

    @BeforeEach
    void setUp() {
        lottoGameDao = LottoGameDao.getInstance();
        initWinningNumberDto = new WinningNumberDto();
        initWinningNumberDto.setNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        initWinningNumberDto.setBonusNumber(7);
    }

    @Test
    void 현재_라운드가_1인지_확인_테스트() throws SQLException {
        assertThat(lottoGameDao.currentRound()).isEqualTo(1);
    }

    @Test
    void 라운드_추가_조회_테스트() throws SQLException {
        LottoDao lottoDao = LottoDao.getInstance();
        lottoDao.removeAllLotto(TEST_ROUND);
        lottoGameDao.removeRound(TEST_ROUND);

        int money = 2000;
        int countOfManual = 2;
        LottoGameDto lottoGameDto = new LottoGameDto(TEST_ROUND, money, countOfManual);

        lottoGameDao.addRound(lottoGameDto);
        assertThat(lottoGameDao.findRoundById(TEST_ROUND).getRound()).isEqualTo(1);
    }
}
