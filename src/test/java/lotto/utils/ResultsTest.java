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
    private static final int RESULT_BASE = 5;
    List<LottoNumber> winningNumbers = new ArrayList<LottoNumber>(Arrays.asList(
            new LottoNumber(WINNING_LOTTO_NUMBERS[0]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[1]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[2]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[3]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[4]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[5])));
    LottoNumber bonusNumber = new LottoNumber(BONUS_NUMBER);
    WinningLottoTicket winningLotto = new WinningLottoTicket(winningNumbers, bonusNumber);

    List<LottoNumber> notWinningUserLottoNumbers = new ArrayList<LottoNumber>(Arrays.asList(
            new LottoNumber("7"),
            new LottoNumber("8"),
            new LottoNumber("9"),
            new LottoNumber("10"),
            new LottoNumber("11"),
            new LottoNumber("12")));
    LottoTicket notWinningUserLottoTicket = new LottoTicket(notWinningUserLottoNumbers);

    List<LottoNumber> secondWinningUserLottoNumbers = new ArrayList<LottoNumber>(Arrays.asList(
            new LottoNumber(WINNING_LOTTO_NUMBERS[0]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[1]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[2]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[3]),
            new LottoNumber(WINNING_LOTTO_NUMBERS[4]),
            new LottoNumber(BONUS_NUMBER)));
    LottoTicket secondWinningUserLottoTicket = new LottoTicket(secondWinningUserLottoNumbers);


    @Test
    void calculateResults_당첨되지_않았을_때() {
        Results results = new Results(Arrays.asList(notWinningUserLottoTicket), winningLotto);
        results.calculateResults();
        assertThat(results.getResults().get(0 + RESULT_BASE).getWinningInfo().name()).isEqualTo(WinningInfo.FAIL.name());
    }

    @Test
    void calculateResults_당첨이_존재할_때() {
        Results results = new Results(Arrays.asList(notWinningUserLottoTicket, secondWinningUserLottoTicket), winningLotto);
        results.calculateResults();
        assertThat(results.getResults().get(0 + RESULT_BASE).getWinningInfo().name()).isEqualTo(WinningInfo.FAIL.name());
        assertThat(results.getResults().get(1 + RESULT_BASE).getWinningInfo().name()).isEqualTo(WinningInfo.SECOND.name());
    }

    @Test
    void getEarningRate() {
        Results results = new Results(Arrays.asList(notWinningUserLottoTicket, secondWinningUserLottoTicket), winningLotto);
        results.calculateResults();
        assertThat(results.getEarningRate()).isEqualTo(1500000);
    }
}
