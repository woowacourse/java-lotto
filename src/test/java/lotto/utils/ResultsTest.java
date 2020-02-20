package lotto.utils;

import lotto.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest {
    private static final String[] WINNING_LOTTO_NUMBERS = {"1", "2", "3", "4", "5", "6"};
    private static final String BONUS_NUMBER = "7";
    List<LottoNumber> winningNumbers = new ArrayList<LottoNumber>(Arrays.asList(
            new LottoNumber(WINNING_LOTTO_NUMBERS[0]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[1]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[2]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[3]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[4]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[5])));
    LottoNumber bonusNumber = new LottoNumber(BONUS_NUMBER);
    WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

    List<LottoNumber> notWinningUserLottoNumbers = new ArrayList<LottoNumber>(Arrays.asList(
            new LottoNumber("7"),
            new LottoNumber("8"),
            new LottoNumber("9"),
            new LottoNumber("10"),
            new LottoNumber("11"),
            new LottoNumber("12")));
    Lotto notWinningUserLotto = new Lotto(notWinningUserLottoNumbers);

    List<LottoNumber> secondWinningUserLottoNumbers = new ArrayList<LottoNumber>(Arrays.asList(
            new LottoNumber(WINNING_LOTTO_NUMBERS[0]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[1]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[2]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[3]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[4]),
            new LottoNumber(BONUS_NUMBER)));
    Lotto secondWinningUserLotto = new Lotto(secondWinningUserLottoNumbers);


    @Test
    void calculateResults_당첨되지_않았을_때() {
        Results results = new Results(Arrays.asList(notWinningUserLotto), winningLotto);
        results.calculateResults();
        assertThat(results.getResults().get(0).getWinningInfo()).isEqualTo(WinningInfo.FAIL);
    }

    @Test
    void calculateResults_당첨이_존재할_때() {
        Results results = new Results(Arrays.asList(notWinningUserLotto, secondWinningUserLotto), winningLotto);
        results.calculateResults();
        assertThat(results.getResults().get(0).getWinningInfo()).isEqualTo(WinningInfo.FAIL);
        assertThat(results.getResults().get(1).getWinningInfo()).isEqualTo(WinningInfo.SECOND);
    }

    @Test
    void getEarningRate() {
        Results results = new Results(Arrays.asList(notWinningUserLotto, secondWinningUserLotto), winningLotto);
        results.calculateResults();
        assertThat(results.getEarningRate()).isEqualTo(15000);
    }
}
