package lotto.utils;

import lotto.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest {
    private static final String[] WINNING_LOTTO_NUMBERS = {"1", "2", "3", "4", "5", "6"};
    private static final String BONUS_NUMBER = "7";
    private static final String[] NOT_WINNING_LOTTO_NUMBERS = {"11", "12", "13", "14", "15", "16"};

    private List<LottoNumber> winningNumbers = LottoGenerator.createLottoNumbersByUserInput(WINNING_LOTTO_NUMBERS);
    private LottoNumber bonusNumber = new LottoNumber(BONUS_NUMBER);
    private WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

    private List<LottoNumber> notWinningUserLottoNumbers = LottoGenerator.createLottoNumbersByUserInput(NOT_WINNING_LOTTO_NUMBERS);
    private Lotto notWinningUserLotto = new Lotto(notWinningUserLottoNumbers);

    private List<LottoNumber> secondWinningUserLottoNumbers = new ArrayList<LottoNumber>(Arrays.asList(
            new LottoNumber(WINNING_LOTTO_NUMBERS[0]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[1]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[2]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[3]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[4]),
            new LottoNumber(BONUS_NUMBER)));
    private Lotto secondWinningUserLotto = new Lotto(secondWinningUserLottoNumbers);


    @Test
    void calculateResults_당첨되지_않았을_때() {
        Results results = new Results(new Lottos(Arrays.asList(notWinningUserLotto)), winningLotto);
        results.calculateResults();
        assertThat(results.getResults().get(0).isSameWinning(WinningInfo.FAIL)).isTrue();
    }

    @Test
    void calculateResults_당첨이_존재할_때() {
        Results results = new Results(new Lottos(Arrays.asList(notWinningUserLotto)), winningLotto);
        results.calculateResults();
        assertThat(results.getResults().get(0).isSameWinning(WinningInfo.FAIL));
        assertThat(results.getResults().get(1).isSameWinning(WinningInfo.SECOND));
    }

    @Test
    void getEarningRate() {
        final int EXPECTED_EARNING_RATE = 15000;
        Results results = new Results(new Lottos(Arrays.asList(notWinningUserLotto)), winningLotto);
        results.calculateResults();
        assertThat(results.getEarningRate()).isEqualTo(EXPECTED_EARNING_RATE);
    }
}
