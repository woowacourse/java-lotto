package lotto.utils;

import lotto.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultsTest {
    @Test
    void calculateResults_당첨되지_않았을_때() {
        List<LottoNumber> userNumbers = new ArrayList<LottoNumber>(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("6")));
        Lotto userLotto = new Lotto(userNumbers);

        List<LottoNumber> winningNumbers = new ArrayList<LottoNumber>(Arrays.asList(
                new LottoNumber("7"),
                new LottoNumber("8"),
                new LottoNumber("9"),
                new LottoNumber("10"),
                new LottoNumber("11"),
                new LottoNumber("12")));
        LottoNumber bonusNumber = new LottoNumber("13");
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);


        Results results = new Results(Arrays.asList(userLotto), winningLotto);
        results.calculateResults();
        Assertions.assertThat(results.getResults().get(0).getWinningInfo()).isEqualTo(WinningInfo.FAIL);
    }
}
