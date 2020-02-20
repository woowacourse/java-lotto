package lotto.utils;

import lotto.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {
    private static final String[] WINNING_LOTTO_NUMBERS = {"1", "2", "3", "4", "5", "6"};
    private static final String BONUS_NUMBER = "7";
    private static final String[] ALL_FAIL_LOTTO_NUMBERS = {"11", "12", "13", "14", "15", "16"};

    private List<LottoNumber> winningNumbers = LottoGenerator.createLottoNumbersByUserInput(WINNING_LOTTO_NUMBERS);
    private LottoNumber bonusNumber = new LottoNumber(BONUS_NUMBER);
    private WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

    private List<LottoNumber> allFailUserLottoNumbers = LottoGenerator.createLottoNumbersByUserInput(ALL_FAIL_LOTTO_NUMBERS);
    private Lotto allFailUserLotto = new Lotto(allFailUserLottoNumbers);

    @Test
    void hasBonus() {
        List<LottoNumber> hasBonusLottoNumbers = new ArrayList<LottoNumber>(Arrays.asList(
                new LottoNumber(ALL_FAIL_LOTTO_NUMBERS[0]),
                new LottoNumber(ALL_FAIL_LOTTO_NUMBERS[1]),
                new LottoNumber(ALL_FAIL_LOTTO_NUMBERS[2]),
                new LottoNumber(ALL_FAIL_LOTTO_NUMBERS[3]),
                new LottoNumber(ALL_FAIL_LOTTO_NUMBERS[4]),
                new LottoNumber(BONUS_NUMBER)));
        Lotto hasBonusLotto = new Lotto(hasBonusLottoNumbers);
        assertThat(ResultCalculator.getHasBonus(hasBonusLotto, winningLotto)).isTrue();

        Lotto notHasBonusLotto = new Lotto(allFailUserLottoNumbers);
        assertThat(ResultCalculator.getHasBonus(notHasBonusLotto, winningLotto)).isFalse();
    }

    @Test
    void getWinningCount() {
        final int ALL_FAIL_WINNING_COUNT = 0;
        final int FIRST_WINNING_COUNT = 6;

        assertThat(ResultCalculator.getWinningCount(allFailUserLotto, winningLotto)
                == ALL_FAIL_WINNING_COUNT);

        Lotto firstWinningLotto = new Lotto(winningNumbers);
        assertThat(ResultCalculator.getWinningCount(firstWinningLotto, winningLotto)
                == FIRST_WINNING_COUNT);
    }

    @Test
    void getTotalEarning() {
        int expected = 2000000000;
        Lotto firstWinningLotto = new Lotto(winningNumbers);
        Lottos lottos = new Lottos(Arrays.asList(firstWinningLotto));
        ArrayList<WinningInfo> result = ResultCalculator.getResult(lottos, winningLotto);

        assertThat(ResultCalculator.getTotalEarning(result)).isEqualTo(expected);
    }

    @Test
    void getEarningRate() {
        int expected = 2000000;
        int totalEarning = 2000000000;
        int lottoSize = 1;
        assertThat(ResultCalculator.getEarningRate(totalEarning, lottoSize)).isEqualTo(expected);
    }

}
