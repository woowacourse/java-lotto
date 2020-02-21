package lotto.utils;

import lotto.domain.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {
    @Test
    void getTotalEarning() {
        final int MIN_LOTTO_NUMBER = 1;
        final int LOTTO_NUMBER_SIZE = 6;
        Set<LottoNumber> winningNumbers = new HashSet<>();
        for (int i = MIN_LOTTO_NUMBER; i <= LOTTO_NUMBER_SIZE; i++) {
            winningNumbers.add(new LottoNumber(i));
        }
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        int expected = 2000000000;
        Lotto firstWinningLotto = new Lotto(winningNumbers);
        Lottos lottos = new Lottos(Arrays.asList(firstWinningLotto));
        List<WinningInfo> result = ResultCalculator.calculateResults(lottos, winningLotto);

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
