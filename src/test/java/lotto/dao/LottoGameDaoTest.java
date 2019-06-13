package lotto.dao;

import lotto.dto.LottoGameDto;
import lotto.dto.WinningNumberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameDaoTest {
    private static final int TEST_ROUND = 1;
    LottoGameDao lottoGameDao;

    @BeforeEach
    void setUp() {
        lottoGameDao = new LottoGameDao();
    }

    @Test
    void 라운드_추가_조회_테스트() throws SQLException {
        lottoGameDao.removeRound(TEST_ROUND);

        LottoGameDto lottoGameDto = new LottoGameDto();
        lottoGameDto.setRound(TEST_ROUND);
        lottoGameDto.setMoney(2000);
        lottoGameDto.setCountOfManual(2);

        lottoGameDao.addRound(lottoGameDto);
        assertThat(lottoGameDao.findRoundById(TEST_ROUND).getRound()).isEqualTo(1);
    }

    @Test
    void 해당_라운드의_당첨번호_조회() throws SQLException {
        WinningNumberDto winningNumberDto = lottoGameDao.findWinningLottoByRound(TEST_ROUND);
        assertThat(winningNumberDto.getNumbers()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(winningNumberDto.getBonusBall()).isEqualTo(7);
    }
}
