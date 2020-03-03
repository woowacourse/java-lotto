package lotto.domain;

import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest {
    private static final String BONUS_NUMBER = "7";
    private static final int RESULT_BASE = 6;
    NumberGenerator numberGenerator;
    List<LottoNumber> winningNumbers;
    LottoNumber bonusNumber;
    WinningLottoTicket winningLotto;
    List<LottoNumber> notWinningUserLottoNumbers;
    LottoTicket notWinningUserLottoTicket;
    List<LottoNumber> secondWinningUserLottoNumbers;
    LottoTicket secondWinningUserLottoTicket;

    @BeforeEach
    void init() {
        numberGenerator = new UserInputNumberGenerator();
        winningNumbers = numberGenerator.generateNumbers("1,2,3,4,5,6");
        bonusNumber = new LottoNumber(BONUS_NUMBER);
        winningLotto = new WinningLottoTicket(winningNumbers, bonusNumber);

        notWinningUserLottoNumbers = numberGenerator.generateNumbers("7,8,9,10,11,12");
        notWinningUserLottoTicket = new LottoTicket(notWinningUserLottoNumbers);

        secondWinningUserLottoNumbers = numberGenerator.generateNumbers("1,2,3,4,5,7");
        secondWinningUserLottoTicket = new LottoTicket(secondWinningUserLottoNumbers);
    }

    @Test
    void calculateResultsTest_당첨되지_않았을_때() {
        Results results = new Results(new LottoTickets(Arrays.asList(notWinningUserLottoTicket)), winningLotto);
        results.calculateResults();
        assertThat(results.getResults()
                .get(0 + RESULT_BASE)
                .getWinningInfo()
                .name())
                .isEqualTo(WinningInfo.FAIL.name());
    }

    @Test
    void calculateResultsTest_당첨이_존재할_때() {
        Results results = new Results(new LottoTickets(Arrays.asList(notWinningUserLottoTicket, secondWinningUserLottoTicket)), winningLotto);
        results.calculateResults();
        assertThat(results.getResults()
                .get(0 + RESULT_BASE)
                .getWinningInfo()
                .name())
                .isEqualTo(WinningInfo.FAIL.name());
        assertThat(results.getResults()
                .get(1 + RESULT_BASE)
                .getWinningInfo()
                .name())
                .isEqualTo(WinningInfo.SECOND.name());
    }

    @Test
    void getEarningRateTest() {
        Results results = new Results(new LottoTickets(Arrays.asList(notWinningUserLottoTicket, secondWinningUserLottoTicket)), winningLotto);
        results.calculateResults();
        assertThat(results.getEarningRate())
                .isEqualTo(1500000);
    }
}
