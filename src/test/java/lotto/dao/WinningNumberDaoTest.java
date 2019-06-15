package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.WinningNumber;
import lotto.dto.WinningNumberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static lotto.dao.LottoGameDaoTest.TEST_ROUND;
import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

public class WinningNumberDaoTest {

    WinningNumberDao winningNumberDao;
    WinningNumber initWinningNumber;

    @BeforeEach
    void setUp() {
        winningNumberDao = WinningNumberDao.getInstance();

        Lotto winningLotto = new Lotto(generateLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        int bonusNumber = 7;
        initWinningNumber = new WinningNumber(winningLotto, bonusNumber);
    }

    @Test
    void 첫번째_라운드_당첨번호_일치_여부_테스트() throws SQLException {
        WinningNumberDto winningNumberDto = winningNumberDao.findWinningLottoByRound(TEST_ROUND);
        assertThat(winningNumberDto.toEntity()).isEqualTo(initWinningNumber);
    }
}
