package lotto.generator;

import lotto.domain.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {
    MatchResults getResult() {
        Lotto lotto = new LottoSelectedGenerator("1,2,3,4,5,6").create();

        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        return  ResultCalculator.computeMatchResults(new Lottos(Arrays.asList(lotto)), winningLotto);
    }

    @Test
    void getTotalEarning() {
        int expected = 2000000000;
        MatchResults result = getResult();
        assertThat(ResultCalculator.computeTotalEarning(result)).isEqualTo(expected);
    }

    @Test
    void getEarningRate() {
        int expected = 2000000;
        int totalEarning = 2000000000;
        int lottoSize = 1;
        assertThat(ResultCalculator.computeEarningRate(totalEarning, lottoSize)).isEqualTo(expected);
    }
}
